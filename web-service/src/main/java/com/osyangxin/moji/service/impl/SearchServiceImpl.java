package com.osyangxin.moji.service.impl;

import com.osyangxin.moji.model.vo.SearchItem;
import com.osyangxin.moji.service.SearchService;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;


/**
 * @author yangxin666
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Override
	public List<SearchItem> search(String key, int page, int size, String sort, int priceGt, int priceLte) {
		return Collections.emptyList();
	}

	@Override
	public String quickSearch(String key) {

		return null;
	}
}
