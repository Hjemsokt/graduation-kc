package cn.kc.graduation.rss;

import cn.kc.graduation.rss.model.Record;
import cn.kc.graduation.rss.model.StockInDO;
import cn.kc.graduation.rss.model.StockType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonTest {
	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Record record =  new Record();
		record.setName("精酿苹果醋");
		record.setId(105l);
		record.setType("饮料");
		record.setSize("312ml");
		record.setNumber(100l);
		record.setSupplierID(1015l);
		record.setValue(300d);
		String json = mapper.writeValueAsString(record);
		System.out.println(json);
		Record in = mapper.readValue(json, Record.class);
		System.out.println(in);
	}
}
