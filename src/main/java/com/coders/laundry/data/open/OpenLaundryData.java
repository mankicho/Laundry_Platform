package com.coders.laundry.data.open;

import com.coders.laundry.jackson.StringLowerCaseDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;


@Data
@JsonDeserialize(using = StringLowerCaseDeserializer.class)
public class OpenLaundryData {

  private String sigunNm;
  private String bizplcNm;
  private String refineRoadnmAddr;
  private String refineLotnoAddr;
  private String bsnStateNm;
  private String refineWgs84Lat;
  private String refineWgs84Logt;
}

/*
{
"SIGUN_NM":"가평군",
"BIZPLC_NM":"뉴세탁나라",
"REFINE_ZIPNO":"12437",
"REFINE_ROADNM_ADDR":"경기도 가평군 조종면 연인산로 20-5",
"REFINE_LOTNO_ADDR":"경기도 가평군 조종면 현리 253-3번지 ",
"LICENSG_DE":"20010725",
"BSN_STATE_NM":"영업",
"CLSBIZ_DE":"",
"MULTI_UTLZ_BIZEST_YN":"N",
"WHM_CNT":"",
"RETRVL_DRY_CNT":"",
"SANITTN_INDUTYPE_NM":"",
"SANITTN_BIZCOND_NM":"빨래방업",
"REFINE_WGS84_LAT":"37.8218966979",
"REFINE_WGS84_LOGT":"127.3517000679"
}
 */
