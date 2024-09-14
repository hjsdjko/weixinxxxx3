package com.example.controller;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.GoumaiInfo;
import com.example.dao.GoumaiInfoDao;
import com.example.entity.Yonghu;
import com.example.service.GoumaiInfoService;
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.EchartsData;
import com.example.vo.GoumaiInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/goumaiInfo")
public class GoumaiInfoController {

    @Resource
    private GoumaiInfoService goumaiInfoService;
	@Resource
    private GoumaiInfoDao goumaiInfoDao;
	@Resource
    private YonghuService yonghuService;

    @PostMapping
    public Result<GoumaiInfo> add(@RequestBody GoumaiInfoVo goumaiInfo) {

		//mixmajixami
		goumaiInfoService.add(goumaiInfo);
        return Result.success(goumaiInfo);
    }
    @PostMapping("/goumai")
    public Result<GoumaiInfo> goumai(@RequestBody GoumaiInfoVo goumaiInfo) {
        Long goumairen = goumaiInfo.getGoumairen();
        Yonghu yonghu = yonghuService.findById(goumairen);
        String userMima = yonghu.getMima();
        String mima = goumaiInfo.getMima();
        if(!SecureUtil.md5(mima).equals(userMima)){
            goumaiInfoService.add(goumaiInfo);
        }else{
            return Result.error("400","支付密码错误");
        }
        //mixmajixami
        return Result.success(goumaiInfo);
    }

	//youtixing1
    //youtixing2

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        goumaiInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody GoumaiInfoVo goumaiInfo) {
        goumaiInfoService.update(goumaiInfo);
        return Result.success();
    }
    //@PutMapping("/update2")
//    public Result update2(@RequestBody GoumaiInfoVo goumaiInfo) {
//        goumaiInfoService.update2(goumaiInfo);
//        return Result.success();
//    }
    @GetMapping("/{id}")
    public Result<GoumaiInfo> detail(@PathVariable Long id) {
        GoumaiInfo goumaiInfo = goumaiInfoService.findById(id);
        return Result.success(goumaiInfo);
    }
    @GetMapping("/getByUserId/{userId}")
    public Result<List<GoumaiInfoVo>> getByZhanghao(@PathVariable Long userId) {
        return Result.success(goumaiInfoService.getByUserId(userId));
    }

//    @GetMapping("/changeStatus/{id}")
//    public Result<GoumaiInfo> changeStatus(@PathVariable Long id) {
//        goumaiInfoService.changeStatus(id);
//        return Result.success();
//    }
    @PostMapping("/status/{id}/{status}")
    public Result status(@PathVariable Long id, @PathVariable String status) {
        goumaiInfoService.changeStatus(id, status);
        return Result.success();
    }

    @GetMapping
    public Result<List<GoumaiInfoVo>> all() {
        return Result.success(goumaiInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<GoumaiInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(goumaiInfoService.findPage(name, pageNum, pageSize, request));
    }
    @GetMapping("/scdd/{issh}/{goumairen}")
    public Result<List<GoumaiInfo>> scdd(@PathVariable("issh") String issh,@PathVariable("goumairen") String goumairen) {
        return Result.success(goumaiInfoService.scdd(issh,goumairen));
    }

	 @GetMapping("/pageqt/{name}")
    public Result<PageInfo<GoumaiInfoVo>> pageqt(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "8") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(goumaiInfoService.findPageqt(name, pageNum, pageSize, request));
    }

   // @PostMapping("/register")
//    public Result<GoumaiInfo> register(@RequestBody GoumaiInfo goumaiInfo) {
//        if (StrUtil.isBlank(goumaiInfo.getName()) || StrUtil.isBlank(goumaiInfo.getPassword())) {
//            throw new CustomException(ResultCode.PARAM_ERROR);
//        }
//        return Result.success(goumaiInfoService.add(goumaiInfo));
//    }

    /**
    * 批量通过excel添加信息
    * @param file excel文件
    * @throws IOException
    */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {

        List<GoumaiInfo> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(GoumaiInfo.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            // 处理一下空数据
            List<GoumaiInfo> resultList = infoList.stream().filter(x -> ObjectUtil.isNotEmpty(x.getShangpinmingcheng())).collect(Collectors.toList());
            for (GoumaiInfo info : resultList) {
                goumaiInfoService.add(info);
            }
        }
        return Result.success();
    }
    @GetMapping("/get/goumai_tj_goumaishuliang")
    Result<List<EchartsData>> goumai_tj_goumaishuliang() {
        List<EchartsData> list = new ArrayList<>();
        List<Map<String, Object>> goumai_tj_goumaishuliangList = goumaiInfoDao.goumai_tj_goumaishuliang();
        Map<String, Double> typeMap = new HashMap<>();
        for (Map<String, Object> map : goumai_tj_goumaishuliangList) {

            typeMap.put((String)map.get("aa"), (Double.valueOf((String)map.get("bb").toString())));

        }
        getPieData("购买按购买数量统计", list, typeMap);
        getBarData("购买按购买数量统计", list, typeMap);
        return Result.success(list);
    }
    @GetMapping("/get/goumai_tj_goumaijine")
    Result<List<EchartsData>> goumai_tj_goumaijine() {
        List<EchartsData> list = new ArrayList<>();
        List<Map<String, Object>> goumai_tj_goumaijineList = goumaiInfoDao.goumai_tj_goumaijine();
        Map<String, Double> typeMap = new HashMap<>();
        for (Map<String, Object> map : goumai_tj_goumaijineList) {

            typeMap.put((String)map.get("aa"), (Double.valueOf((String)map.get("bb").toString())));

        }
        getPieData("购买按购买金额统计", list, typeMap);
        getBarData("购买按购买金额统计", list, typeMap);
        return Result.success(list);
    }
	//yoxutonxgjitu
    @GetMapping("/getExcelModel")
    public void getExcelModel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
		row.put("shangpinbianhao", "A农产品编号");
row.put("shangpinmingcheng", "A农产品名称");
row.put("shangpinleibie", "A农产品类别");
row.put("jiage", "A价格");
row.put("kucun", "A库存");
row.put("xiaoliang", "A销量");
row.put("goumaishuliang", "A购买数量");
row.put("goumaijine", "A购买金额");
row.put("goumairen", "A购买人");

		row.put("status", "是");
		row.put("level", "goumai");

        List<Map<String, Object>> list = CollUtil.newArrayList(row);

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=goumaiInfoModel.xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }
	@GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("shangpinbianhao", "A农产品编号");
row.put("shangpinmingcheng", "A农产品名称");
row.put("shangpinleibie", "A农产品类别");
row.put("jiage", "A价格");
row.put("kucun", "A库存");
row.put("xiaoliang", "A销量");
row.put("goumaishuliang", "A购买数量");
row.put("goumaijine", "A购买金额");
row.put("goumairen", "A购买人");


        row.put("status", "是");
        row.put("level", "权限");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = goumaiInfoDao.daochuexcel();
        Map<String, Double> typeMap = new HashMap<>();
        for (Map<String, Object> map : daochuexcellist) {
            list.add(map);
        }
        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=goumaiInfo.xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }
	private void getPieData(String name, List<EchartsData> pieList, Map<String, Double> dataMap) {
        EchartsData pieData = new EchartsData();
        EchartsData.Series series = new EchartsData.Series();

        Map<String, String> titleMap = new HashMap<>(2);
        titleMap.put("text", name);
        pieData.setTitle(titleMap);

        series.setName(name + "比例");
        series.setType("pie");
        series.setRadius("55%");

        List<Object> objects = new ArrayList<>();
        List<Object> legendList = new ArrayList<>();
        for (String key : dataMap.keySet()) {
            Double value = dataMap.get(key);
            objects.add(new JSONObject().putOpt("name", key).putOpt("value", value));
            legendList.add(key);
        }
        series.setData(objects);

        pieData.setSeries(Collections.singletonList(series));
        Map<String, Boolean> map = new HashMap<>();
        map.put("show", true);
        pieData.setTooltip(map);

        Map<String, Object> legendMap = new HashMap<>(4);
        legendMap.put("orient", "vertical");
        legendMap.put("x", "left");
        legendMap.put("y", "center");
        legendMap.put("data", legendList);
        pieData.setLegend(legendMap);

        pieList.add(pieData);
    }

    private void getBarData(String name, List<EchartsData> barList, Map<String, Double> dataMap) {
        EchartsData barData = new EchartsData();
        EchartsData.Series series = new EchartsData.Series();

        List<Object> seriesObjs = new ArrayList<>();
        List<Object> xAxisObjs = new ArrayList<>();
        for (String key : dataMap.keySet()) {
            Double value = dataMap.get(key);
            xAxisObjs.add(key);
            seriesObjs.add(value);
        }

        series.setType("bar");
        series.setName(name);
        series.setData(seriesObjs);
        barData.setSeries(Collections.singletonList(series));

        Map<String, Object> xAxisMap = new HashMap<>(1);
        xAxisMap.put("data", xAxisObjs);
        barData.setxAxis(xAxisMap);

        barData.setyAxis(new HashMap<>());

        Map<String, Object> legendMap = new HashMap<>(1);
        legendMap.put("data", Collections.singletonList(name));
        barData.setLegend(legendMap);

        Map<String, Boolean> map = new HashMap<>(1);
        map.put("show", true);
        barData.setTooltip(map);

        Map<String, String> titleMap = new HashMap<>(1);
        titleMap.put("text", name);
        barData.setTitle(titleMap);

        barList.add(barData);
    }
}
