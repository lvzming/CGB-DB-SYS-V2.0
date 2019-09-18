package com.tedu.cgb.team.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class Page<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer pageCurrent;
	private Integer pageSize;
	private Integer rowCount;
	private Integer pageCount;
	private List<T> records;
	public Page(Integer pageCurrent, Integer pageSize, Integer rowCount, List<T> records) {
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.records = records;
		this.pageCount = (rowCount-1) / pageSize+1;
	}
	
}
