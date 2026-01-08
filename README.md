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
![img.png](img.png)

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
@PathVariable: 获取url的路径参数
@bulider: 链式编程
@PathVariable: 获取url的路径参数,常见是 GET / DELETE
```
```mysql
<where>： 动态SQL，满足条件才拼接SQL
like + % : 模糊查询
order by xxx desc/asc: 降序/升序
<set>: 自动加 SET 关键字,处理逗号
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
```
- 空指针：访问对象属性时，对象为null
- 反射（Reflection）：程序在运行时，查看和操作“类本身”的能力
  - Method 类： 方法的描述对象，能调用这个方法
  - invoke(谁, 用什么参数)： 调用方法，
- 方法签名（MethodSignature）：对“一个方法”的完整描述对象，包括方法名 参数类型 返回值 注解