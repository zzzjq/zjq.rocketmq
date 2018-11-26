package zjq.work.consumer;

import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * 集群消费模式消费者
 * 
 * @author zhangjq
 * @date 2018年11月23日下午3:41:11
 *
 **/
public class ClusterConsumer extends DefaultConsumer {

	@Override
	public void init() {
		super.setMessageModel(MessageModel.CLUSTERING);
		super.init();
	}
}
