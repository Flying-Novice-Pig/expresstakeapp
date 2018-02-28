package com.graduationdesign.expresstakeapp;
import com.graduationdesign.expresstakeapp.indentManage.bo.Indent;
import com.graduationdesign.expresstakeapp.indentManage.service.impl.IndentManageImpl;
import com.graduationdesign.expresstakeapp.indentManage.service.interfaces.IndentManage;
import com.graduationdesign.expresstakeapp.util.ApplicationContextProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ExpressapptakeApplicationTests {



    @Test
	public void contextLoads() {
    	new Thread(new Runnable() {
			@Override
			public void  run() {

				IndentManage indentManage1 = ApplicationContextProvider.getBean("IndentManageImpl",IndentManageImpl.class);
                System.out.println(indentManage1);
//				Indent indent = new Indent();
//				indent.setIndentWealth(5);
//				indent.setSenderName("lisi");
//				indent.setSenderPhone("18755082101");
//				indent.setSenderAddress("安徽省合肥市合肥学院46栋");
//				indent.setExpressId(6002);
//				indent.setExpressAddress("安徽省合肥市合肥学院华伦影院");
//				indent.setIndentCode(1002);
//				indent.setExpressType(2);
//				indent.setExpressSize(1);
//				ApplicationContextProvider.getBean("IndentManageImpl",IndentManageImpl.class).addIndent(indent);

			}
		}).start();



    		new Thread(new Runnable() {
				@Override
				public void run() {
					IndentManage indentManage2 = ApplicationContextProvider.getBean("IndentManageImpl",IndentManageImpl.class);
					System.out.println(indentManage2);
//					Indent indent = new Indent();
//					indent.setIndentWealth(5);
//					indent.setSenderName("zhangsan");
//					indent.setSenderPhone("18755082100");
//					indent.setSenderAddress("安徽省合肥市合肥学院46栋");
//					indent.setExpressId(6001);
//					indent.setExpressAddress("安徽省合肥市合肥学院华伦影院");
//					indent.setIndentCode(1001);
//					indent.setExpressType(2);
//					indent.setExpressSize(1);
//					ApplicationContextProvider.getBean("IndentManageImpl",IndentManageImpl.class).addIndent(indent);
				}
			}).start();






//		Indent indent = new Indent();
//		indent.setIndentWealth(5);
//		indent.setSenderName("zhangsan");
//		indent.setSenderPhone("18755082100");
//		indent.setSenderAddress("安徽省合肥市合肥学院46栋");
//		indent.setExpressId(6001);
//		indent.setExpressAddress("安徽省合肥市合肥学院华伦影院");
//		indent.setIndentCode(1001);
//		indent.setExpressType(2);
//		indent.setExpressSize(1);
//		indentManage.addIndent(indent);




	}

}
