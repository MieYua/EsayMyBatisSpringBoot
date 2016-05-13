# EsayMyBatisSpringBoot #
Json, RESTful API, Maven

---

## Esay MyBatis Spring Boot Version ##
* <strong>Ver. 1.1 (Released on 160511)</strong>

---

## Java SDK Version ##
* <strong>Java 1.8 or Higher</strong>

## Spring Boot Version ##
* <strong>Ver. 1.3.3.RELEASE</strong>

## MyBatis Version ##
* <strong>Ver. 3.3.1</strong>

---


## Contents ##
[0 说在前头 | Tall about the service at first](#tall-about-the-service-at-first)  
[1 配置文件 | Configuration](#configuration)  
[1.1 数据库配置 | MySQL configurations](#config-sql)  
[1.2 其它的配置 | All configurations](#config-all)  
[1.3 时间的格式 | Time Configuration](#config-time)  
[2 代码修改 | How to use](#how-to-use)  
[2.1 数据库映射类设置 | Model setting](#model-setting)  
[2.2 自定义数据库操作（增加、修改） | Additional database handler(Inert&Update)](#additional-database-handler-1)  
[2.3 自定义数据库操作（查询） | Additional database handler(Select)](#additional-database-handler-2)  
[2.4 服务调用路由设置 | Controller setting](#controller-setting)  
[3 使用接口 | Request examples](#request-examples)  
[3.1 获取所有账号 | Get all records](#get-all)  
[3.2 获取指定账号 | Get the record by id](#get-by-id)  
[3.3 简单新建账号 | Insert new record](#post)  
[3.4 更新指定账号 | Update the record by id](#put)  
[3.5 删除指定账号 | Delete the record by id](#delete)  
[3.6 获取指定账号的自定义字段 | Get all account's additional records by accountId](#get-by-accountid)  
[3.7 获取指定账号的自定义字段（分页） | Get all account's additional records by accountId into pages](#get-by-accountid-into-pages)  
[4 简单文件服务历史版本 | Esay MyBatis Spring Boot History](#esay-mybatis-spring-boot-history)  

---

## Tall about the service at first ##
[Top](#contents)

* 第一次使用MyBatis+Spring Boot的框架；
* 网上的教程太复杂，故精简了简单开发版；
* 将[abel533/MyBatis-Spring-Boot](https://github.com/abel533/MyBatis-Spring-Boot)进行简化。

---

## Configuration ##
[Top](#contents)


### Config SQL ###
[Top](#contents)

* 使用`src\main\resources\test.sql`数据库（MySQL 5.5以上）导入（可修改）

		USE `my_test`;
		
		CREATE TABLE `my_account` (
		  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'account_id自增id',
		  `username` varchar(20) NOT NULL COMMENT '用户名',
		  `password` varchar(20) NOT NULL COMMENT '密码',
		  `register_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
		  `description` varchar(100) DEFAULT NULL COMMENT '描述',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试账号表';
		
		CREATE TABLE `my_account_addition` (
		  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'account自定键值',
		  `account_id` int(11) NOT NULL COMMENT 'account表的id',
		  `addition_key` varchar(50) NOT NULL COMMENT '增加的键名称',
		  `addition_value` varchar(50) NOT NULL COMMENT '增加的值',
		  `description` varchar(100) DEFAULT NULL COMMENT 'account自定键值描述',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
		
		-- 账号信息
		INSERT INTO `my_account` VALUES ('1', 'root', 'root', CURRENT_TIMESTAMP, '管理员账号');
		INSERT INTO `my_account` VALUES ('2', 'test', 'test', CURRENT_TIMESTAMP, '测试账号');
		INSERT INTO `my_account` VALUES ('3', 'person', 'yes', CURRENT_TIMESTAMP, '测试账号');
		
		-- 账号自定义字段
		INSERT INTO `my_account_addition` VALUES ('1', '1', 'nickname', '管理员', '增加昵称字段');
		INSERT INTO `my_account_addition` VALUES ('2',	'1', 'sex', '男', '增加性别字段');
		INSERT INTO `my_account_addition` VALUES ('3', '2', 'nickname', '测试账号', '增加昵称字段');
		INSERT INTO `my_account_addition` VALUES ('4',	'2', 'sex', '女', '增加性别字段');
		INSERT INTO `my_account_addition` VALUES ('5', '3', 'nickname', '测试账号2', '增加昵称字段');
		INSERT INTO `my_account_addition` VALUES ('6',	'3', 'test', '测试', '增加测试字段');

### Config All ###
[Top](#contents)

* 配置文件`src\main\resources\application.yml`：
		
		# 服务器配置
		server:
			# 端口号
		    port: 4004
			# 自定路径，例如RESTful的 /v1
		    context-path: /v1
		
		# Spring配置
		spring:
			# 数据库配置
		    datasource:
				# 连接名
		        name: test
				# url: jdbc:sqlserver://localhost:3306/my_test
				# 中文乱码问题：再加 ?useUnicode=true&characterEncoding=utf8
		        url: jdbc:mysql://localhost:3306/my_test?useUnicode=true&characterEncoding=utf8
				# 用户名
		        username: root
				# 密码
		        password: ******
			# 之后可以默认配置
				# 使用druid数据源
				# type: com.alibaba.druid.pool.DruidDataSource
				# driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
		        type: com.alibaba.druid.pool.DruidDataSource
		        driver-class-name: com.mysql.jdbc.Driver
		        filters: stat
		        maxActive: 20
		        initialSize: 1
		        maxWait: 60000
		        minIdle: 1
		        timeBetweenEvictionRunsMillis: 60000
		        minEvictableIdleTimeMillis: 300000
		        validationQuery: select 'x'
		        testWhileIdle: true
		        testOnBorrow: false
		        testOnReturn: false
		        poolPreparedStatements: true
		        maxOpenPreparedStatements: 20
		    freemarker:
		        cache: false
		        request-context-attribute: request


### Config Time ###
[Top](#contents)

* Model：
	* 显示完整时间	java.sql.Timestamp
	* 仅显示年月日	java.sql.Date
	* 仅显示时分秒	java.sql.Date

* MapperXML(ResultMap)：
	* 显示完整时间	jdbcType="TIMESTAMP"
	* 仅显示年月日	jdbcType="DATE"
	* 仅显示时分秒	jdbcType="TIME"

---

## How to Use ##
[Top](#contents)

### Model Setting ###
[Top](#contents)

* model类`src\main\java\top.mieyua.my\model`；
* 以Account为例`src\main\java\top.mieyua.my\model\Account.java`：

		// 导包
		// JsonProperty注解包
		import com.fasterxml.jackson.annotation.JsonProperty;
		
		// 数据库表的列注解包
		import javax.persistence.Column;
		// 数据库表的id注解包
		import javax.persistence.Id;
		// 数据库表的注解包
		import javax.persistence.Table;
		import java.sql.Date;
		
		// 表名称
		@Table(name = "my_account")
		public class Account {
			// id注解
		    @Id
			// 对应表的列名称
		    @Column(name = "id")
			// Json格式的名称
		    @JsonProperty("id")
		    private int id;
			...
		}

### Additional Database Handler 1 ###
[Top](#contents)

* 首先配置映射配置xml文件`src\main\java\resources\mapper\AccountMapper.xml`：

		<?xml version="1.0" encoding="UTF-8" ?>
		<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
		<mapper namespace="top.mieyua.my.mapper.AccountMapper">
		    <!-- 自定义增操作（时间不用插入） -->
			<insert id="insertAccount" parameterType="top.mieyua.my.model.Account" useGeneratedKeys="true" keyProperty="id">
		        insert into my_account(username, password, description)
		        values(#{username}, #{password}, #{description})
		    </insert>
			<!-- 自定义改操作（时间不能修改） -->
		    <update id="updateAccountByPrimaryKey" parameterType="top.mieyua.my.model.Account" useGeneratedKeys="true" keyProperty="id">
		        update my_account set username = #{username}, password = #{password}, description = #{description} where id = #{id}
		    </update>
		</mapper>

* 在mapper类中声明方法`src\main\java\top.mieyua.my\mapper\AccountMapper.java`：

		public interface AccountMapper extends MyMapper<Account> {
		    public void insertAccount(Account account);
		
		    public void updateAccountByPrimaryKey(Account account);
		}

* 在service中使用方法`src\main\java\top.mieyua.my\service\AccountService.java`：

		@Service
		public class AccountService {
			
		    @Autowired
		    private AccountMapper accountMapper;
			
			...
			
		    public void saveAccount(Account account) {
		        if (account.getId() != null && account.getId() != 0) {
		            accountMapper.updateAccountByPrimaryKey(account);
		        } else {
		            accountMapper.insertAccount(account);
		        }
		    }
		}

* 最后在controller里使用，以POST请求为例`src\main\java\top.mieyua.my\controller\AccountController.java`：

		@RestController
		@RequestMapping("/account")
		public class AccountController {
		
		    @Autowired
		    private AccountService accountService;
			
			...
			
		    @RequestMapping(value = "/", method = RequestMethod.POST)
		    public ModelMap add(@RequestBody String jsonString) {
		        ModelMap result = new ModelMap();
		        HTTPResponse hp = new HTTPResponse();
		        String msg = "";
		        int code = 200;
		
		        ObjectMapper mapper = new ObjectMapper();
		        Account account = new Account();
		        try {
		            account = mapper.readValue(jsonString.getBytes(), Account.class);
		        } catch (Exception e) {
		            msg = "Json信息读取失败：" + e.toString();
		            code = 410;
		            result = hp.getResponse(code, msg, null);
		            return result;
		        }
		
		        if (account.getId() != null && account.getId() != 0) {
		            msg = "新增失败：该账号已经存在";
		            code = 410;
		            result = hp.getResponse(code, msg, null);
		            return result;
		        }
		        try {
		            accountService.saveAccount(account);
		            msg = "成功新增数据";
		            code = 201;
		            result = hp.getResponse(code, msg, null);
		        } catch (Exception e) {
		            msg = "服务器错误：" + e.toString();
		            code = 503;
		            result = hp.getResponse(code, msg, null);
		            return result;
		        }
		        return result;
		    }
		}

### Additional Database Handler 2 ###
[Top](#contents)

* 首先配置映射配置xml文件`src\main\java\resources\mapper\AccountAdditionMapper.xml`：

		<?xml version="1.0" encoding="UTF-8" ?>
		<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
		<mapper namespace="top.mieyua.my.mapper.AccountAdditionMapper">
		    <resultMap id="additionMap" type="top.mieyua.my.model.AccountAddition">
		        <id property="id" column="id" />
				# 这里的property要和model里定义的参数名相同，column为数据库的列名
		        <result property="accountId" column="account_id" />
		        <result property="additionKey" column="addition_key" />
		        <result property="additionValue" column="addition_value" />
		        <result property="description" column="description" />
		    </resultMap>
		    <select id="selectByAccountId" parameterType="Integer" resultMap="additionMap">
		        select * from my_account_addition where account_id = #{account_id}
		    </select>
		</mapper>

* 在mapper类中声明方法`src\main\java\top.mieyua.my\mapper\AccountAdditionMapper.java`：

		public interface AccountAdditionMapper extends MyMapper<AccountAddition> {
		    public List<AccountAddition> selectByAccountId(Integer accountId);
		}

* 至此，之后的用法则与上面的[增加、修改](#additional-database-handler-1)相同。


### Controller Setting ###
[Top](#contents)

* controller类`src\main\java\top.mieyua.my\controller`；
* 以AccountController为例`src\main\java\top.mieyua.my\controller\AccountController.java`：

		// RestController注解
		@RestController
		// 请求路由后置注解
		@RequestMapping("/v1/account")
		public class AccountController {
			// 自动注入
		    @Autowired
		    private AccountService accountService;
			
			// GET所有请求，返回json数据
		    @RequestMapping(value = "/", method = RequestMethod.GET)
		    public ModelMap getAll(Account account) {
		        ...
		        return result;
		    }
			
			// GET对应id请求，返回json数据
		    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
		    public ModelMap view(@PathVariable Integer id) {
		        ...
		        return result;
		    }
			
			// POST提交请求，用于新建，返回json数据
		    @RequestMapping(value = "/", method = RequestMethod.POST)
		    public ModelMap add(@RequestBody String jsonString) {
		        ...
		        return result;
		    }
			
			// PUT对应id请求，用于修改，返回json数据
		    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
		    public ModelMap save(@PathVariable Integer id,@RequestBody String jsonString) {
		        ...
		        return result;
		    }
			
			// DELETE对应id请求，用于删除，返回json数据
		    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		    public ModelMap delete(@PathVariable Integer id) {
		        ...
		        return result;
		    }
		}

* 以AccountAdditionController为例`src\main\java\top.mieyua.my\controller\AccountAdditionController.java`：

		// RestController注解
		@RestController
		// 请求路由后置注解
		@RequestMapping("/accountAddition")
		public class AccountAdditionController {
			// 自动注入
		    @Autowired
		    private AccountAdditionService accountAdditionService;
			
			// GET请求，返回json数据
		    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
			// @Param("")可以获取当前路由?后的参数
		    public ModelMap viewStockByPage(@PathVariable Integer accountId, @Param("p") Integer p, @Param("n") Integer n) {
		        ...
			    return result;
			}
		}

---

## Request Examples ##
[Top](#contents)

### Get all ###
[Top](#contents)

*	获得所有数据

*	请求示例：
		
		GET http://URL:port/v1/account/
		
*	成功返回：
	
		{
			"meta":{"code":200,"message":"成功查找数据"},
			"data":[
				{"id":1,"username":"root","password":"root","register_time":"2016-05-06","description":"管理员账号"},
				{"id":2,"username":"test","password":"test","register_time":"2016-05-06","description":"测试账号"},
				{"id":3,"username":"person","password":"yes","register_time":"2016-05-06","description":"测试账号"}
			]
		}

### Get by Id ###
[Top](#contents)

*	获得指定id（int account_id）的数据
 
*	请求示例：

		GET http://URL:port/v1/account/account_id
		
*	成功返回：

		{
			"meta":{"code":200,"message":"成功查找数据"},
			"data":{"id":1,"username":"root","password":"root","register_time":"2016-05-06","description":"管理员账号"}
		}

*	错误返回：

		{
			"meta":{"code":503,"message":"没有对应数据"},
			"data":null
		}

### Post ###
[Top](#contents)

*	简单数据新建  

*	请求示例：

		POST http://URL:port/v1/account/
		Body(Json)
		{
			// "id" id主键自增，不用写入，写入报已存在的错
			"username":"person2",
			"password":"yes2",
			//"register_time" 注册时间自动生成，不用写入
			"description":"新建测试"	
		}

*	成功返回：
		
		{
			"meta":{"code":201,"message":"成功新增数据"},
			"data":null
		}

*	错误返回：

		{
			"meta":{"code":410,"message":"新增失败：该账号已经存在"},
			"data":null
		}
		

### Put ###
[Top](#contents)

*	更新简单数据（int account_id）

*	请求示例：

		PUT http://URL:port/v1/account/account_id
		Body(Json)
		{
			"id":account_id, // id需要与account_id相同，不同报修改对象不同的错
			"username":"person2p",
			"password":"yes2p",
			//"register_time" 注册时间自动生成，不能修改
			"description":"修改测试"	
		}

*	成功返回：

		{
			"meta":{"code":200,"message":"成功修改数据"},
			"data":null
		}

*	错误返回：

		{
			"meta":{"code":410,"message":"更新失败：想要修改的账号与请求不符合"},
			"data":null
		}

### Delete ###
[Top](#contents)

*	删除指定数据（int account_id，不可恢复）

*	请求示例：

		DELETE http://URL:port/v1/account/account_id

*	成功返回：

		{
			"meta":{"code":204,"message":"成功删除数据"},
			"data":null
		}

*	错误返回：

		{
			"meta":{"code":503,"message":"服务器错误：org.springframework.jdbc.BadSqlGrammarException:..."},
			"data":null
		}


### Get by AccountId ###
[Top](#contents)

*	获得指定accountId（int account_id）的自定义字段信息
 
*	请求示例：

		GET http://URL:port/v1/account/account_id
		
*	成功返回：

		{
			"meta":{"code":200,"message":"成功查找数据"},
			"data":[
				{"id":1,"account_id":1,"addition_key":"nickname","addition_value":"管理员","description":"增加昵称字段"},
				{"id":2,"account_id":1,"addition_key":"sex","addition_value":"男","description":"增加性别字段"}
			]
		}


### Get by AccountId into Pages ###
[Top](#contents)

*	获得指定accountId（int account_id）的自定义字段信息（分页?p=vp&n=vn，vp为页码，vn为每页个数）
 
*	请求示例：

		GET http://URL:port/v1/account/account_id?p=1&n=1
		
*	成功返回：

		{
			"meta":{"code":200,"message":"成功查找数据"},
			"data":{
				"page_info":{
					"current_page":1,
					"items_per_page":1,
					"total_page":2
				},
				"products":[
				{"id":1,"account_id":1,"addition_key":"nickname","addition_value":"管理员","description":"增加昵称字段"}
				]
			}
		}

---

## Esay MyBatis Spring Boot History ##
[Top](#contents)

### Ver. 1.0 (20160506) ###
* 原始版本：
	* RESTful接口；
	* Json数据解析；
	* 简单数据库实例；
	* 简单MyBatis增删改查的Spring Boot服务。

### Ver. 1.1 (20160511) ###
* 新增AccountAddition（账号自定义字段表）；
* 增加自定义select功能；
* 增加可分页路由。

---

