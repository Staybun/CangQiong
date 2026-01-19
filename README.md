# Recording
## tag 即为 dayN 的文件目录
### day-1
- pojo: 只有属性,getter / setter,就是个普通对象
- Entity: 和数据库表一一对应
- DTO: Data Transfer Object, 前端传过来的数据
- VO: 给前端展示用
- controller: 接 HTTP 请求,返回响应,用 DTO 接参数
- service: 业务
- mapper: 写数据库的CRUD

```txt
开发：application-dev.yml（本地数据库）
测试：application-test.yml（测试库）
线上：application-prod.yml（线上库）

interceptor（拦截器）
handler（全局异常）
config（各种 Bean 配置）
```
- MD5: 哈希算法，把任意长度的数据 → 固定长度的指纹，即32位字符串，不可逆
> 123456  MD5—>  e10adc3949ba59abbe56e057f20f883e
> > 已被淘汰，加密程度不够
- TODO: 标记未完成的事
- FIXME： 有bug，需要修复
- NOTE： 记录一些笔记，刻意说明
- YApi: 接口管理
- Knife4j：接口文档
- Swagger：接口文档
- apifox：推荐使用，本人所用，替代swagger

### day-2
- Controller层：浏览器/前端发请求过来，Controller负责接受并调用Service处理，最后把结果打包为Result返回给前端
- ThreadLocal: 每次请求是一个线程，ThreadLocal保证每个线程有自己独立的数据副本，不会冲突，请求结束后需要remove，否则会内存泄漏（黑马未操作）
- Result 不带泛型：只返回“状态+提示”（没有 data）
- Result<T> 带泛型：要返回“数据 data”，T 就是 data 的类型
```java
@RestController: REST接口控制器，返回值会直接写到 HTTP 响应体里，默认为json
@RequestMapping("/admin/employee")：给此Controller统一加前缀路径
@Slf4j： Lombok自动生成log对象
@Autowired： 依赖注入DI
@PostMapping("/login")：POST请求接口
@RequestBody: 把前端发来的 JSON请求体 自动解析成 Java 对象, 常见是 POST / PUT
@Data:  Lombok自动生成getter/setter/toString/equals/hashCode
@AllArgsConstructor: 全参构造
@NoArgsConstructor：无参构造
@bulider: 链式编程
@PathVariable: 获取url的路径参数,常见是 GET / DELETE
```
```mysql
<where>： 动态SQL，满足条件才拼接SQL
like + % : 模糊查询
order by xxx desc/asc: 降序/升序
<set>: 自动加 SET 关键字,处理逗号
<foreach>: 遍历集合
    collection="list"，集合名字参数
    item="dishFlavor"，集合元素名字参数
    separator=","，集合元素之间用逗号拼接
```

### day-3
- AOP
  - 切面（Aspect）：把“横着切进去的逻辑”集中写在一个类里
  - 切入点（Pointcut）：切入点 = 我要拦截哪些方法
    - execution(...) —— 匹配方法签名
    > * com.sky.mapper.*.*(..)  execution(返回值 包名.类名.方法名(参数))
    > * 拦截 com.sky.mapper 包下，任意类、任意方法、任意参数的方法
  - 通知（Advice）: 在什么时机执行切面逻辑
  > @Before：之前
  > @After：之后（不管成功失败）
  > @AfterReturning：成功后
  > @AfterThrowing：异常后
  > @Around：前后都包
  - JoinPoint: 包含： 被拦的方法名,方法参数,目标对象,方法签名

```java
@Target(ElementType.METHOD): 自定义注解 @AutoFill 只能贴在“方法”上
@Retention(RetentionPolicy.RUNTIME):这个注解会“保留到运行时”，程序运行时能通过反射读到它
@Component: spring管理
@Aspect： 切面类
@Transactional: 事务
@RequestParam: 从 URL 的查询参数里拿值
```
- 空指针：访问对象属性时，对象为null
- 反射（Reflection）：程序在运行时，查看和操作“类本身”的能力
  - Method 类： 方法的描述对象，能调用这个方法
  - invoke(谁, 用什么参数)： 调用方法，
- 方法签名（MethodSignature）：对“一个方法”的完整描述对象，包括方法名 参数类型 返回值 注解

- 部署： 把程序/服务放到一台【别人也能访问的服务器】上运行
- OSS： 对象存储（Object Storage），存文件，并给一个公网可访问的 URL
  - Bucket = OSS 里的一个“存储空间 / 文件桶”

### day-4 (作业)

### day-5
- Spring Data Redis
  - RedisTemplate: 操作 Redis 的模板类
  - .opsForXXX().XXX  操作redis数据类型的方法函数
  - 序列化与反序列化配置
- final 关键词： 创建对象时，不允许被修改，常量标准写法
```java
@Configuration: 配置类, 配置类里可以写 @Bean , 用来向Spring容器中注册对象
@SpringBootTest: 单元测试
```

### day-6
- HttpClient = 让 Java 后端，像 Postman/浏览器一样去访问别人的接口
    - 调第三方接口
    - 微服务互调
    - 后台定时任务
- 创建客户端,创建请求对象,execute 发送,读状态码 & 读响应体
- 微信小程序
    - code: 临时票据
    - appid/appsecret 
    - openid: 用户在小程序下的唯一编号
    - session_key: 微信给的会话密钥
    - token: 用户信息,给小程序后续访问用
    - storage: 小程序本地仓库
