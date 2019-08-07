# OneNET 趋势监控

通过调用 OneNET SDK，获取一段时间内的温度值，并在前端展示温度曲线。

![tempr-trend][1]

### 开发环境
1. jdk 1.8
2. maven 3.0+，构建工具
3. IntelliJ IDEA，IDE
4. git，版本维护工具

### OneNET SDK

按照官方文档的说法，OneNET 提供了 SDK 供应用程序调用，其中 NB-IoT 应该使用 LWM2M API SDK。不过我尝试了一下，发现这个 SDK 功能极其有限，仅能完成 NB 设备的创建、读写、订阅等操作，并不能获取数据点。

![sdk-desc][2]

然而，MQTT 的 SDK 功能则比较完善，实验验证也能够获取数据点，因此使用这个 SDK 来获取数据点，本例中获取的是一段时间以内的温度值。

```
git clone https://github.com/cm-heclouds/JAVA-HTTP-SDK.git
```

编译这个项目，会生成 *javaSDK.jar*，它就是访问 OneNET 的工具包。使用如下语句将其安装到本地 Maven 仓库（需要修改 *javaSDK.jar* 的路径）：

```
mvn install:install-file -DgroupId=cmcc.iot.onenet.javasdk -DartifactId=javaSDK -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -Dfile=E:\your\path\to\javaSDK.jar
```

然后，就可以在我们的 SpringBoot 项目的 *pom.xml* 中引用这个工具包了。项目中是这样引用的：

```
<dependency>
	<groupId>cmcc.iot.onenet.javasdk</groupId>
	<artifactId>javaSDK</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

### 编译运行

修改 *resources\application.yml* 中的产品 *apiKey* 和设备 *deviceId*，当然，该“产品->设备”下需要存在温度数据流才可以看到数据。另外还可以调整 *webapp\index.html* 中的 Ajax 请求参数，比如调整时间区间等。

在 IDEA 中点击 maven clean 和 maven package，编译成功后，打开 *MonitorApplication.java*，在 `main()` 函数上点击右键执行程序。

在浏览器中：

- 输入 http://127.0.0.1:8081/datapoints 可以查看到 OneNET 平台返回的数据点
- 输入 http://127.0.0.1:8081/index.html 可以查看温度趋势图
- 输入 http://127.0.0.1:8081/swagger-ui.html 可以查看本程序实现的接口

### 代码说明

在 *DataPointsQueryService* 中，调用 SDK 提供的 `GetDatapointsListApi` 类，即可以访问数据点：

```
@Service
public class DataPointsQueryService {
    @Resource
    OneNetConfig oneNetConfig;

    public final Logger logger = LoggerFactory.getLogger(getClass());

    public JSONObject query(DataPointsQueryRequestBean requestBean) {

        // 调用 OneNET SDK 查询数据点
        GetDatapointsListApi api = new GetDatapointsListApi(
                requestBean.getDatastreamId(),
                requestBean.getStart(),
                requestBean.getEnd(),
                oneNetConfig.getDeviceId(),
                null,
                Integer.parseInt(requestBean.getLimit()),
                requestBean.getCursor(),
                null,
                null,
                null,
                null,
                oneNetConfig.getApiKey());

        BasicResponse<DatapointsList> response = api.executeApi();
        logger.info(response.toString());

        return JSONObject.parseObject(response.getJson());
    }
}
```

  [1]: https://github.com/CQCET-IOT/monitor2/raw/master/img/tempr-trend.jpg
  [2]: https://github.com/CQCET-IOT/monitor2/raw/master/img/sdk-desc.png
