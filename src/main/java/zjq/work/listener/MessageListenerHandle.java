package zjq.work.listener;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消费消息事件源
 * 
 * @author zhangjq
 * @date 2018年11月23日下午2:39:40
 *
 **/
public class MessageListenerHandle implements MessageListenerConcurrently {

	private static final Logger logger = LoggerFactory.getLogger(MessageListenerHandle.class);

	private ConcurrentHashMap<String, ConsumerListener> container;

	private ExecutorService e;

	public MessageListenerHandle(ExecutorService e, ConcurrentHashMap<String, ConsumerListener> container) {
		this.e = e;
		this.container = container;
	}

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		MessageExt message = msgs.get(0);
		MessageConsumerListener listener = (MessageConsumerListener) container.get(message.getTopic());
		e.submit(new Runnable() {

			@Override
			public void run() {
				try {
					listener.onMessage(message, context);
				} catch (Exception e) {
					logger.error("consumer message exception, msg:{}, error:{}", message, e);
				}
			}
		});
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}
