package com.tedu.sp09.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tedu.sp01.pojo.Item;
import com.tedu.web.util.JsonResult;
//通知指定服务id，向哪一台主机转发调用
//http://localhost:8001
//http://localhost:8002
@FeignClient(name="item-service",fallback = ItemFeignServiceFB.class)
public interface ItemFeignService {
	/**
	 * Feign 利用springmvc注解
	 * 反向生成访问路径
	 * 
	 * @PathVariable 配置
	 * http://localhost:8001/{orderId}
	 * 
	 * 向拼接路径转发调用
	 */
	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId);

	@PostMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items);
}
