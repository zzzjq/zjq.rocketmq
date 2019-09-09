package zjq.rocketmq.listener;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 抽象消费消息类
 * 
 * @author zhangjq
 * @date 2018年11月23日下午2:27:50
 *
 **/
public abstract class MessageConsumerListener implements ConsumerListener {

	/**
	 * 抽象消费消息方法
	 * 
	 * @param message
	 * @param context
	 * @return
	 */
	public abstract boolean onMessage(MessageExt message, ConsumeConcurrentlyContext context);

}
