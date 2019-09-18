package com.tedu.cgb.team.common.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SysRoleMenuVo {
	private Integer id;
	private String name;
	private String note;
	private List<Integer> menuIds;
}
