package sprittr.dao;


import org.apache.ibatis.type.Alias;
import org.mybatis.spring.annotation.MapperScan;

import util.Conditions;


@MapperScan
@Alias("AsResultMap")	
public class AsResultMap extends Conditions {

}
