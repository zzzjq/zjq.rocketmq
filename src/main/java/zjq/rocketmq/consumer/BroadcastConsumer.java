package zjq.rocketmq.consumer;

import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * 广播消费模式消费者
 * 
 * @author zhangjq
 * @date 2018年11月23日下午3:43:34
 *
 **/
public class BroadcastConsumer extends DefaultConsumer {

	@Override
	public void init() {
		super.setMessageModel(MessageModel.BROADCASTING);
		super.init();
	}
}
