package cn.blz.service;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient("product")
public interface BioProductService {
}
