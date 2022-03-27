package com.example.data;

import com.example.data.demo.service.RegionEntityService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootExampleApplication.class)
public class RegionTest {


    @Autowired
    private RegionEntityService regionEntityService;


    @Test
    public void insert() throws Exception {
//        String path = "/Users/seven/Downloads/";
//        String file = path + "20220323 主题库行政区划.xlsx";
//        List<RegionEntity> regions = ExcelHandler.readExcel(RegionEntity.class, new FileInputStream(file));
//
//        regions.parallelStream().forEach(item->{
//            String regionCode = getRealRegionCode(item.getRegionCode());
//            item.setGrade(getGrade(regionCode));
//            item.setParentCode(addZero(regionCode));
//            regionEntityService.save(item);
//        });
    }


    public static Integer getGrade(String regionCode) {

        if (StringUtils.isBlank(regionCode)) {
            return null;
        }

         else if (regionCode.endsWith("0000000000")) {
            return 0;
        } else if (regionCode.endsWith("00000000")) {
            return 1;
        } else if (regionCode.endsWith("000000")) {
            return 2;
        } else if (regionCode.endsWith("000")) {
            return 3;
        }
        return 4;
    }




    public static String getRealRegionCode(String regionCode) {

        if (StringUtils.isBlank(regionCode)) {
            return regionCode;
        }
        if (regionCode.endsWith("000000000000")) {
            regionCode = "";
        } else if (regionCode.endsWith("0000000000")) {
            regionCode = regionCode.substring(0, 2);
        } else if (regionCode.endsWith("00000000")) {
            regionCode = regionCode.substring(0, 4);
        } else if (regionCode.endsWith("000000")) {
            regionCode = regionCode.substring(0, 6);
        } else if (regionCode.endsWith("000")) {
            regionCode = regionCode.substring(0, 9);
        }
        return regionCode;
    }


    /**
     * 补0
     *
     * @return
     */
    public static String addZero(String regionCode) {
        regionCode = regionCode + "000000000000";
        return regionCode.substring(0, 12);
    }

}
