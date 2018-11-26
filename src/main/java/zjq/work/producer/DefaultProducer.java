package zjq.work.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息发送者
 * 
 * @author zhangjq
 * @date 2018年11月23日下午4:59:14
 *
 **/
public class DefaultProducer {

	static final private Logger logger = LoggerFactory.getLogger(DefaultProducer.class);

	private String nameAddrs;

	private String group;

	private DefaultMQProducer producer;

	private String instanceName;

	/**
	 * 显示发送消息大小，最大512k
	 */
	private final int maxMessageSize = 1024 * 512;

	public void init() {
		logger.info("DefaultProducer启动开始");
		producer = new DefaultMQProducer(group);
		producer.setNamesrvAddr(nameAddrs);
		if (instanceName != null) {
			producer.setInstanceName(instanceName);
		}
		producer.setMaxMessageSize(maxMessageSize);
		producer.setSendMsgTimeout(1000);
		// 关闭VIP通道，避免出现connect to <:10909> failed导致消息发送失败
		producer.setVipChannelEnabled(false);
		try {
			producer.start();
		} catch (Exception e) {
			logger.error("DefaultProducer启动异常，error:{}", e);
		}
		logger.info("DefaultProducer启动结束");
	}

	public SendResult send(Message message) {
		logger.info("发送消息开始，message:{}", message);
		SendResult result = null;
		try {
			result = producer.send(message);
		} catch (Exception e) {
			logger.error("发送消息失败，message:{}, error:{}", message, e);
		}
		logger.info("发送消息结束，message:{}, result:{}", message, result);
		return result;
	}

	public void setNameAddrs(String nameAddrs) {
		this.nameAddrs = nameAddrs;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

}
