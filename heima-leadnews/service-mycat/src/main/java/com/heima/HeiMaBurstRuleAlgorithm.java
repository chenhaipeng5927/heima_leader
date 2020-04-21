package com.heima;

import io.mycat.config.model.rule.RuleAlgorithm;
import io.mycat.route.function.AbstractPartitionAlgorithm;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: chenhp
 * @Date: 2020/04/11/14:05
 * @Description: 自定义多字段算法计算
 */
public class HeiMaBurstRuleAlgorithm extends AbstractPartitionAlgorithm implements RuleAlgorithm {
	// 单组数据容量
	Long volume;
	// 单组DN节点数量
	Integer step;
	// 分片模
	Integer mod;

	@Override
	public void init() {
		super.init();
	}

	/**
	 * @Description:
	 * @Author: chenhp
	 * @Date: 2020/4/11 14:08
	 * @Param: columnValue 数据ID-桶ID
	 * @Return: java.lang.Integer
	 */
	@Override
	public Integer calculate(String columnValue) {
		if (columnValue != null) {
			String[] temp = columnValue.split("-");
			if (temp.length == 2) {
				try {
					Long dataId = Long.valueOf(temp[0]);
					Long burstId = Long.valueOf(temp[1]);
					int group = (int) (dataId / volume) * step;
					int pos = group + (int) (burstId % mod);
					System.out.println("HEIMA RULE INFO [" + columnValue + "]-[{" + pos + "}]");
					return pos;
				} catch (Exception e) {
					System.out.println("HEIMA RULE INFO [" + columnValue + "]-[{" + e.getMessage() + "}]");
				}
			}
		}
		return 0;
	}

	/**
	 * @Description: 范围计算
	 * @Author: chenhp
	 * @Date: 2020/4/11 14:13
	 * @Param: beginValue endValue
	 * @Return: java.lang.Integer[]
	 */
	@Override
	public Integer[] calculateRange(String beginValue, String endValue) {
		if (beginValue != null && endValue != null ){
			Integer begin = calculate(beginValue);
			Integer end = calculate(endValue);
			if (begin == null || end == null) {
				return new Integer[0];
			}
			if (end >= begin) {
				int len = end - begin + 1;
				Integer[] re = new Integer[len];
				for (int i = 0; i < len; i++) {
					re[i] = begin + i;
				}
				return re;
			}
		}
		return new Integer[0];
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public void setMod(Integer mod) {
		this.mod = mod;
	}
}
