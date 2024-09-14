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
import com.example.entity.ShangpinxinxiInfo;
import com.example.dao.ShangpinxinxiInfoDao;
import com.example.service.ShangpinxinxiInfoService;
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.EchartsData;
import com.example.vo.ShangpinxinxiInfoVo;
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
@RequestMapping(value = "/shangpinxinxiInfo")
public class ShangpinxinxiInfoController {

    @Resource
    private ShangpinxinxiInfoService shangpinxinxiInfoService;
	@Resource
    private ShangpinxinxiInfoDao shangpinxinxiInfoDao;

    @PostMapping
    public Result<ShangpinxinxiInfo> add(@RequestBody ShangpinxinxiInfoVo shangpinxinxiInfo) {
        
		//mixmajixami
		shangpinxinxiInfoService.add(shangpinxinxiInfo);
        return Result.success(shangpinxinxiInfo);
    }
	
	@GetMapping("/tixing")
    public Result<Integer> tixing() { Integer count = shangpinxinxiInfoService.tixing(); return Result.success(count); }
	
	@GetMapping("/getByDiqu")
    public Result<List<Map<String,String>>> qidu() {
        return Result.success(shangpinxinxiInfoService.findByDiqu());
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        shangpinxinxiInfoService.delete(id);
        return Result.success();
    }


    @PutMapping
    public Result update(@RequestBody ShangpinxinxiInfoVo shangpinxinxiInfo) {
        shangpinxinxiInfoService.update(shangpinxinxiInfo);
        return Result.success();
    }
    //@PutMapping("/update2")
//    public Result update2(@RequestBody ShangpinxinxiInfoVo shangpinxinxiInfo) {
//        shangpinxinxiInfoService.update2(shangpinxinxiInfo);
//        return Result.success();
//    }
    @GetMapping("/{id}")
    public Result<ShangpinxinxiInfo> detail(@PathVariable Long id) {
        ShangpinxinxiInfo shangpinxinxiInfo = shangpinxinxiInfoService.findById(id);
        return Result.success(shangpinxinxiInfo);
    }
    @GetMapping("/changeStatus/{id}")
    public Result<ShangpinxinxiInfo> changeStatus(@PathVariable Long id) {
        shangpinxinxiInfoService.changeStatus(id);
        return Result.success();
    }
	

    @GetMapping
    public Result<List<ShangpinxinxiInfoVo>> all() {
        return Result.success(shangpinxinxiInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<ShangpinxinxiInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(shangpinxinxiInfoService.findPage(name, pageNum, pageSize, request));
    }
    @GetMapping("/front")
    public Result<List<ShangpinxinxiInfoVo>> front() {
        List<ShangpinxinxiInfoVo> list = shangpinxinxiInfoService.findAll();
        if(list.size()>3){
            return Result.success(shangpinxinxiInfoService.findAll().subList(0,3));
        }else {
            return Result.success(shangpinxinxiInfoService.findAll());
        }
    }

    @GetMapping("/pageqt/{name}")
    public Result<PageInfo<ShangpinxinxiInfoVo>> pageqt(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "8") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(shangpinxinxiInfoService.findPageqt(name, pageNum, pageSize, request));
    }
    @GetMapping("/findByType/{type}")
    public Result<List<ShangpinxinxiInfoVo>> findByType(@PathVariable String type) {
        return Result.success(shangpinxinxiInfoService.findByType(type));
    }
    @GetMapping("/searchGoods")
    public Result<List<ShangpinxinxiInfo>> searchGoods(@RequestParam String text) {
        return Result.success(shangpinxinxiInfoService.searchGoods(text));
    }
   // @PostMapping("/register")
//    public Result<ShangpinxinxiInfo> register(@RequestBody ShangpinxinxiInfo shangpinxinxiInfo) {
//        if (StrUtil.isBlank(shangpinxinxiInfo.getName()) || StrUtil.isBlank(shangpinxinxiInfo.getPassword())) {
//            throw new CustomException(ResultCode.PARAM_ERROR);
//        }
//        return Result.success(shangpinxinxiInfoService.add(shangpinxinxiInfo));
//    }

    /**
    * 批量通过excel添加信息
    * @param file excel文件
    * @throws IOException
    */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {

        List<ShangpinxinxiInfo> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(ShangpinxinxiInfo.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            // 处理一下空数据
            List<ShangpinxinxiInfo> resultList = infoList.stream().filter(x -> ObjectUtil.isNotEmpty(x.getShangpinmingcheng())).collect(Collectors.toList());
            for (ShangpinxinxiInfo info : resultList) {
                shangpinxinxiInfoService.add(info);
            }
        }
        return Result.success();
    }
	@GetMapping("/get/shangpinxinxi_tj_shangpinleibie")
    Result<List<EchartsData>> shangpinxinxi_tj_shangpinleibie() {
        List<EchartsData> list = new ArrayList<>();
        List<Map<String, Object>> shangpinxinxi_tj_shangpinleibieList = shangpinxinxiInfoDao.shangpinxinxi_tj_shangpinleibie();
        Map<String, Double> typeMap = new HashMap<>();
        for (Map<String, Object> map : shangpinxinxi_tj_shangpinleibieList) {

            typeMap.put((String)map.get("aa"), (Double.valueOf((String)map.get("bb").toString())));

        }
        getPieData("农产品信息按农产品类别统计", list, typeMap);
        getBarData("农产品信息按农产品类别统计", list, typeMap);
        return Result.success(list);
    }

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
row.put("tupian", "A图片");
row.put("beizhu", "A备注");

		row.put("status", "是");
		row.put("level", "shangpinxinxi");

        List<Map<String, Object>> list = CollUtil.newArrayList(row);

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=shangpinxinxiInfoModel.xlsx");

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
row.put("tupian", "A图片");
row.put("beizhu", "A备注");


        row.put("status", "是");
        row.put("level", "权限");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = shangpinxinxiInfoDao.daochuexcel();
        Map<String, Double> typeMap = new HashMap<>();
        for (Map<String, Object> map : daochuexcellist) {
            list.add(map);
        }
        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=shangpinxinxiInfo.xlsx");

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
	//xixugaixmixma
}