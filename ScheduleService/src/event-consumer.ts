import {Consumer, Kafka} from 'kafkajs';
import eventProducer from './event-produce';
import logger from './logger/logger';
import {Event} from './types/event';

const eventListner = async () => {
  const kafka = new Kafka({
    clientId: process.env.CLIENT_ID,
    brokers: [process.env.BROKER_URL || 'localhost:9092'],
  });

  const consumer: Consumer = kafka.consumer({
    groupId: process.env.CONSUMER_GROUP || 'default',
    retry: {retries: 0},
  });

  logger.info(`subscribing to ${process.env.LISTEN_TOPIC || 'error'}`);
  await consumer
      .subscribe({
        topic: process.env.LISTEN_TOPIC || 'error',
        fromBeginning: true,
      })
      .catch((e) => logger.error(e));

 function getRandomDate(startDate: string, rangeOfDays: number){
    var today = new Date(startDate);
    today.setDate(today.getDate()+1);
    return new Date(today.getFullYear(), today.getMonth(), today.getDate()+Math.random() *rangeOfDays).toLocaleDateString('en-ZA');
  }

  await consumer.run({
    autoCommit: false,
    eachMessage: async ({topic, partition, message}) => {
      logger.debug(`new message : ${message.value?.toString()}`);

      const newMessage: Event = JSON.parse(message.value?.toString() || '{}');

      if (!(newMessage?.type === 'ALLOCATION_COMPLETE')) {
        logger.info(
            `incomming message is not allocated type. (${newMessage?.type}) skipped the process`
        );
        return;
      }
      await new Promise((resolve) => setTimeout(resolve, 12000));
      await eventProducer(process.env.RESPOND_TOPIC || 'error', {
          orderId: newMessage?.orderId,
          type: 'schedule-complete',
          data: getRandomDate(newMessage?.data, 5),
          result: 'success',
          from: process.env.SERVICE_NAME
      } as Event).catch((e) => {
        throw new Error('error on publishing message');
      });
      logger.debug('responded to message');
      await consumer.commitOffsets([
        {
          topic,
          partition,
          offset: (Number(message.offset) + 1).toString(),
        },
      ]);
    },
  });
};

export default eventListner;
