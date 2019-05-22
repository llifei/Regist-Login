package lf.user.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import lf.user.domain.User;

/**
 * 数据类
 * 
 * @author 11031
 *
 */
public class UserDaoImpl implements UserDao{
	private String path = "C:\\Users\\11031\\Documents\\MyEclipse_workspace\\j2ee\\users.xml";// 依赖数据文件

	/**
	 * 按用户名查询
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		/*
		 * 1.得到Document 2.xpath查询！ 3.检验查询结果是否为null,如果为null,返回null
		 * 4.如果不为null,需要把Element封装到User对象中。
		 */
		/*
		 * 1.得到Document
		 */
		// 创建解析器
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(path));
			/*
			 * 2.通过xpath查询得到Element
			 */
			Element ele = (Element) doc.selectSingleNode("//user[@username='" + username + "']");
			/*
			 * 3.检验ele是否为null，如果为null，返回null
			 */
			if (ele == null) {
				return null;
			}
			/*
			 * 4.把ele的数据封装到User对象中
			 */
			User user = new User();
			String aUsername = ele.attributeValue("username");// 获取该元素的名为username属性值
			String aPassword = ele.attributeValue("password");// 获取该元素的名为password属性值
			user.setUsername(aUsername);
			user.setPassword(aPassword);

			return user;
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		/*
		 * 1.得到Document 2.通过Document得到root元素！<users> 3.使用参数user,转发成Element对象
		 * 4.把element添加到root元素中 5.保存Document
		 */

		// 得到Document
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(path));
			// 得到根元素
			Element root = doc.getRootElement();
			// 通过根元素创建新元素
			Element userEle = root.addElement("user");
			// 为userEle设置属性
			userEle.addAttribute("username", user.getUsername());
			userEle.addAttribute("password", user.getPassword());

			/*
			 * 保存文档
			 */
			// 创建输出格式化器
			OutputFormat format = new OutputFormat("\t", true);// 缩进使用\t,是否换行,使用是！
			format.setTrimText(true);// 清空原有的换行和缩进

			// 创建XMLWriter
			XMLWriter writer;
			try {
				writer = new XMLWriter(new OutputStreamWriter(
						new FileOutputStream(path), "UTF-8"), format);
				writer.write(doc);
				writer.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
}
