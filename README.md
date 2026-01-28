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
- 套餐模块： 添加、修改、查询、批量删除、分页查询
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

### day-7
- 缓存：把数据存到redis里，减轻数据库压力
- SpringCache 缓存框架
  - SpringEL（SpEL）= Spring Expression Language： 语法
  - value：缓存命名空间，不是 Redis 的 key，而是 key 的前缀空间
  - key：缓存的唯一标识，#参数名 = 方法参数
```java
// 启动类加，启动时自动注册缓存管理器
@EnableCaching
// 先查缓存,有直接返回，没有就存入缓存，查询接口
@Cacheable(value="xxxCache", key="#xxx")
// 改数据后删除缓存，全删和精致删除，写接口（新增/修改/删除/状态变化）
@CacheEvict(value="setmealCache", allEntries=true/key = "#setmealDTO.categoryId")
```

### day-8
- 微信支付：需要商家认证才能开通,个人开发者可以模拟支付的流程,完成后续项目的学习
  > 参考CSDN苍穹外卖模拟微信支付,或参考tag v1.6 来修改代码

### day-9 (作业)
- 百度地图API
  - 服务端：在后端发HTTP请求时候选用
  - 浏览器端：在前端直接引入百度地图JS选用
### day-10
- Spring Task: 定时任务
```java
@EnableScheduling   // 启动类
@Scheduled(cron="0 0 1 * * ?")  // cron表达式
LocalDateTime.now()  // 当前时间
.plusMinutes(x)  //在当前时间上加x分钟，x可正可负
```
- cron表达式： 从左到右，分别代表：秒 分 时 日 月 星期 年
  - @Scheduled(cron = "0 * * * * ?")  每 1 分钟执行
  - @Scheduled(cron = "0 */5 * * * ?") 每 5 分钟执行
  - @Scheduled(cron = "0 0 1 * * ?")  每天凌晨1点执行
  - 其余可直接让AI生成表达式

- WebSocket：服务器可以主动给客户端推送消息的通信方式
  - 长连接 · 实时 · 双向
```java
@ServerEndpoint("/ws/{sid}")  // WebSocket 的服务器端
@OnOpen
@OnClose
@OnMessage      // 客户端主动发消息
```

### day-11
- Echarts：可视化

### day-12
- Apache POI : Apache 提供的一个 Java 开源库，用于在 Java 程序中读写 Microsoft Office 文件,常用于 Excel 文件的导入与导出

### day-13 技术栈复盘

#### Nginx

- **负载均衡**： 本质是用调度算法，把请求平均或合理分发到多个后端实例，避免单点瓶颈。
- **upstream：后端服务池**
- **调度算法**
  - 轮询，权重，ip_hash（同一用户总打到一台服务器）

- **正向代理：客户端主动通过代理访问外部资源，代理隐藏的是客户端**
- **反向代理： 解决跨域，客户端不知道真实后端服务器的存在，只和代理服务器通信，代理隐藏的是服务端**

#### MD5

- 密码加密，或者称为哈希/摘要，解决**数据库明文存密码**的问题

- 固定长度不可逆

#### Swagger

- 接口文档框架，此项目用**Apifox**代替

#### JWT（JSON Web Token）

- 服务器不存登录态，登录后发放token，之后带着token进
- 安全：密钥+exp过期
- 场景: 身份验证，授权

#### Interceptor（拦截器）

- **preHandle**：决定放不放行
- **afterCompletion**：收尾清理

#### ThreadLocal

- 为什么要用？
  - 在一次请求的同一个线程里，让所有代码都能拿到同一份上下文数据，而不用层层传参

- 怎么用？
  - set：拦截器里放
  - get：业务代码拿
  - remove：请求结束时清理，防止内存泄漏

- 场景：自动填充公共字段，统一获取当前登录用户

  ```txt
  请求进来
  → 拦截器解析 JWT
  → BaseContext.setCurrentId(empId)
  → Controller / Service / AOP
     BaseContext.getCurrentId()
  → 请求结束
  → BaseContext.removeCurrentId()
  ```

####  JWT + Interceptor + ThreadLocal

```
1.前端登录成功拿到 token
2.之后每次请求都带 token
3.拦截器 preHandle 校验 token
4.校验通过解析出 id → BaseContext(ThreadLocal)
5.业务/AOP 需要当前用户时直接 get
6.afterCompletion remove，避免线程复用串号
```

#### 全局异常处理器

- 用于统一捕获系统中抛出的异常，并将其转换为标准化的响应结果，从而避免在各个 Controller 中重复 try-catch，使业务逻辑与异常处理解耦。

#### HttpClient

- 后端用于发起 HTTP 请求的工具，常用于调用第三方接口或其他服务，通常在 Service 层通过工具类封装使用

- 常封装为`doGet / doPos`

#### Spring Cache

- Spring 提供的一套**缓存抽象层**
- 本质是 AOP：在方法调用前后织入“读缓存/写缓存/删缓存”的逻辑

#### Spring Task

- 按固定规则定时执行方法

-  cron 表达式

#### Transactional

- 事务的本质：要么全成功，要么全失败, **这个特性就是原子性**

#### OSS

- 对象存储服务: 存文件的“云硬盘”，通过 HTTP 访问

#### Websocket

- 是一种长连接的通信协议，用来实现服务端主动向客户端实时推送消息

- **axios** 是“客户端主动发请求、服务器被动响应”