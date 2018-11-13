package io.sugo.sql;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by zty on 18-11-8
 */
public class Const {
	public static Map<String, String> getMap() {
		Map<String, String> tableColumnMap = Maps.newHashMap();



		tableColumnMap.put("community_service", "id\n" +
						"title\n" +
						"png\n" +
						"disable_png\n" +
						"reorder\n" +
						"clause\n" +
						"type\n" +
						"is_account\n" +
						"link\n" +
						"provide_type\n" +
						"status\n" +
						"app_id\n" +
						"description\n" +
						"audit_comment\n" +
						"create_time\n" +
						"update_time\n" +
						"audit_time\n" +
						"category_id\n" +
						"is_need_hardware\n" +
						"target_month_turnover\n" +
						"paid_caution_money\n" +
						"caution_money_status\n" +
						"ground_mode\n" +
						"merchant_id\n" +
						"account_id\n" +
						"audit_status\n" +
						"clause_id\n" +
						"app_secret\n" +
						"receivable_caution_money\n" +
						"recommend_level\n" +
						"caution_money_type\n" +
						"caution_money_pic\n" +
						"remark\n" +
						"caution_bill_id\n" +
						"commission_rate\n" +
						"user_range\n" +
						"ground_audit_status\n" +
						"shot\n" +
						"account_receivable_project\n" +
						"notify_url\n" +
						"balance_receive_email\n" +
						"under_reason\n" +
						"ground_time\n" +
						"max_ios_version\n" +
						"min_ios_version\n" +
						"max_andoird_version\n" +
						"min_andoird_version\n" +
						"service_need\n");


		tableColumnMap.put("open_merchant_info","id\n" +
						"email\n" +
						"type\n" +
						"title\n" +
						"title_simple\n" +
						"introduction\n" +
						"business_scope\n" +
						"create_time\n" +
						"update_time\n" +
						"registration_number\n" +
						"business_license\n" +
						"is_other_license\n" +
						"other_license\n" +
						"target_month_turnover\n" +
						"contact_person\n" +
						"contact_phone\n" +
						"province_area_id\n" +
						"city_area_id\n" +
						"area_id\n" +
						"area_info\n" +
						"status\n" +
						"status_content\n" +
						"bank_title\n" +
						"bank_account_name\n" +
						"bank_account\n" +
						"id_number\n" +
						"id_positive\n" +
						"id_obverse\n" +
						"platform_fee\n" +
						"mch_id\n" +
						"account_id\n" +
						"xfsj_status\n" +
						"legal_name\n" +
						"legal_id_card\n" +
						"legal_id_positive\n" +
						"legal_id_back\n" +
						"src_business\n" +
						"src_city\n" +
						"is_mandatary\n" +
						"mandatary_name\n" +
						"mandatary_id_card\n" +
						"mandatary_id_positive\n" +
						"mandatary_id_back\n" +
						"mandatary_other\n");

		tableColumnMap.put("community_service_category", "id\n" +
						"name\n" +
						"create_time\n" +
						"type\n" +
						"sort\n" +
						"exposure\n" +
						"pid\n" +
						"link\n" +
						"png\n" +
						"community_fee\n" +
						"premium_num\n" +
						"valid_status\n");

		tableColumnMap.put("open_service_detail", "service_id\n" +
						"develop_type\n" +
						"templete_name\n" +
						"templete_status\n" +
						"app_index_sort\n" +
						"app_cat_sort\n" +
						"rfchina_code\n" +
						"caution_refund_status\n" +
						"caution_refund_reason\n" +
						"xfsj_status\n" +
						"category_second_type\n" +
						"protocol_url\n" +
						"verify_role\n");

		tableColumnMap.put("community", "id\n" +
						"name\n" +
						"phone\n" +
						"tel\n" +
						"city_id\n" +
						"city_name\n" +
						"area_id\n" +
						"address\n" +
						"intro\n" +
						"status\n" +
						"category\n" +
						"project_guid\n" +
						"sort\n" +
						"qrcode_url\n" +
						"longitude\n" +
						"latitude\n" +
						"admin_id\n" +
						"type\n" +
						"building_area\n" +
						"practical_area\n" +
						"type_desc\n" +
						"logo_url\n" +
						"background_url\n" +
						"topic_audit\n" +
						"create_time\n" +
						"cid\n" +
						"weixin\n" +
						"property_company\n" +
						"business_person\n" +
						"business_phone\n" +
						"business_email\n" +
						"pinyin\n" +
						"sync_flag\n" +
						"update_time\n");

		tableColumnMap.put("area", "id\n" +
						"name\n" +
						"parent_id\n" +
						"short_name\n" +
						"level_type\n" +
						"city_code\n" +
						"zipcode\n" +
						"merger_name\n" +
						"lng\n" +
						"lat\n" +
						"pinyin\n");

		tableColumnMap.put("open_order", "id\n" +
						"total_id\n" +
						"account_id\n" +
						"community_id\n" +
						"app_id\n" +
						"service_id\n" +
						"uid\n" +
						"caution_id\n" +
						"pay_channel\n" +
						"out_trade_no\n" +
						"client_ip\n" +
						"project_type\n" +
						"trade_type\n" +
						"weixin_open_id\n" +
						"expire_time\n" +
						"bussiness_type\n" +
						"create_time\n" +
						"update_time\n" +
						"bill_status\n" +
						"bill_id\n" +
						"bill_create_time\n" +
						"community_trade_no\n" +
						"bill_finished_time\n" +
						"total_amount\n" +
						"platform_charge\n" +
						"platform_charge_rate\n" +
						"service_charge\n" +
						"service_charge_rate\n" +
						"receipt_amount\n" +
						"tixian_thrid\n" +
						"tixian_community\n" +
						"refund_status\n" +
						"refund_reason\n" +
						"refund_id\n" +
						"refund_remark\n" +
						"refund_create_time\n" +
						"refund_money\n" +
						"refund_bill_id\n" +
						"refund_account_id\n" +
						"same_way\n" +
						"withdraw_status\n" +
						"type\n" +
						"service_charge_status\n" +
						"balance_status\n" +
						"service_charge_bill_id\n" +
						"service_account\n" +
						"service_account_mchid\n" +
						"merchant_bill_id\n" +
						"bill_split_status\n" +
						"bill_split_time\n" +
						"split_amount\n" +
						"open_id\n" +
						"out_status\n" +
						"out_status_content\n" +
						"settle_id\n" +
						"pf_balance_status\n" +
						"pf_bussiness_balance_status\n" +
						"pf_refund_status\n" +
						"pf_refund_time\n" +
						"subject\n" +
						"body\n" +
						"detail\n" +
						"goods_detail\n");

		tableColumnMap.put("open_store_order", "id\n" +
						"user_id\n" +
						"open_id\n" +
						"community_id\n" +
						"service_id\n" +
						"addr_info_id\n" +
						"addr_info\n" +
						"addr_detail\n" +
						"user_name\n" +
						"mobile\n" +
						"order_code\n" +
						"out_trade_no\n" +
						"express_money\n" +
						"goods_total\n" +
						"original_price\n" +
						"discount_price\n" +
						"total_amount\n" +
						"coupon_price\n" +
						"verify_status\n" +
						"subject\n" +
						"body\n" +
						"detail\n" +
						"express_type\n" +
						"express_name\n" +
						"express_code\n" +
						"express_time\n" +
						"express_area\n" +
						"express_phone\n" +
						"pay_status\n" +
						"pay_channel\n" +
						"send_status\n" +
						"send_time\n" +
						"refund_time\n" +
						"refund_status\n" +
						"refund_approval\n" +
						"refund_comment\n" +
						"refund_remark\n" +
						"result_status\n" +
						"same_way\n" +
						"open_refund_id\n" +
						"create_time\n" +
						"invalid_time\n" +
						"confirm_time\n" +
						"resource_type\n" +
						"coupon_record_id\n" +
						"type\n" +
						"xfsj_id\n" +
						"remark\n");

		tableColumnMap.put("open_quick_order", "id\n" +
						"uid\n" +
						"community_id\n" +
						"service_id\n" +
						"total_money\n" +
						"subtract_money\n" +
						"coupon_money\n" +
						"real_money\n" +
						"coupon_id\n" +
						"bill_status\n" +
						"out_trade_no\n" +
						"community_trade_no\n" +
						"bill_id\n" +
						"create_time\n" +
						"pay_time\n");

		tableColumnMap.put("open_store_shopping_cart", "id\n" +
						"service_id\n" +
						"user_id\n" +
						"open_id\n" +
						"good_id\n" +
						"amount\n" +
						"create_time\n" +
						"valid_status\n");

		tableColumnMap.put("open_store_good", "id\n" +
						"service_id\n" +
						"code\n" +
						"name\n" +
						"short_detail\n" +
						"pre_price\n" +
						"price\n" +
						"pic_url\n" +
						"status\n" +
						"create_time\n" +
						"update_time\n" +
						"stock\n" +
						"sale_amount\n" +
						"category_id\n" +
						"verify_times\n" +
						"verify_start_time\n" +
						"verify_end_time\n");
		tableColumnMap.put("open_store_good_order_rel", "id\n" +
						"order_id\n" +
						"good_id\n" +
						"current_single_price\n" +
						"amount\n" +
						"good_name\n" +
						"good_code\n" +
						"good_img_url\n" +
						"verify_times\n" +
						"verify_start_time\n" +
						"verify_end_time\n" +
						"pay_status\n");

		return tableColumnMap;
	}

	public static void printColumnStr(Map<String, String> nameMap) {
		Map<String, String> map = getMap();
		for(Map.Entry<String, String> entry : nameMap.entrySet()) {
			boolean isNull = entry.getValue().equals("null");
			String name = entry.getKey();
			String colStr = map.get(name);

			if(colStr == null || colStr.trim().length()==0) {
				System.out.println(name + " is not in name map");
			} else {
				String[] cols = colStr.split("\n");
				for(String col : cols) {
					if(isNull) {
						System.out.println(String.format("null as %s__%s,", name, col));
					} else {
						System.out.println(String.format("%s.%s as %s__%s,", name, col, name, col));
					}
				}
			}
		}
	}

	public static void printColumn(Map<String, String> nameMap) {
		Map<String, String> map = getMap();
		for(Map.Entry<String, String> entry : nameMap.entrySet()) {
			boolean isNull = entry.getValue().equals("null");
			String name = entry.getKey();
			String colStr = map.get(name);

			if(colStr == null || colStr.trim().length()==0) {
				System.out.println(name + " is not in name map");
			} else {
				String[] cols = colStr.split("\n");
				for(String col : cols) {
					if(isNull) {
						System.out.println(String.format("%s__%s,", name, col));
					} else {
						System.out.println(String.format("%s.%s as %s__%s,", name, col, name, col));
					}
				}
			}
		}
	}



	public static void main(String[] args) {
//		printColumnStr(ImmutableMap.<String, String>builder()
//										.put("open_order", "all")
//										.put("open_store_order", "all")
//										.put("community_service", "all")
//										.put("open_merchant_info", "all")
//										.put("community_service_category", "all")
//										.put("open_service_detail", "all")
//										.put("community", "all")
//										.put("area", "all")
//										.build()
//
//		);

//		printColumnStr(ImmutableMap.<String, String>builder()
//						.put("open_order", "all")
//						.put("open_store_order", "all")
//						.put("community_service", "all")
//						.put("open_merchant_info", "all")
//						.put("community_service_category", "all")
//						.put("open_service_detail", "all")
//						.put("community", "all")
//						.put("area", "all")
//						.build()
//		);

//		printColumn(ImmutableMap.<String, String>builder()
//						.put("open_order", "null")
//						.put("open_store_order", "null")
//						.put("open_quick_order", "all")
//						.put("community_service", "null")
//						.put("open_merchant_info", "null")
//						.put("community_service_category", "null")
//						.put("open_service_detail", "null")
//						.put("community", "null")
//						.put("area", "null")
//						.build()
//		);

//		printColumn(ImmutableMap.<String, String>builder()
//						.put("open_order", "null")
//						.put("open_store_order", "null")
//						.put("open_quick_order", "all")
//						.put("community_service", "all")
//						.put("open_merchant_info", "all")
//						.put("community_service_category", "all")
//						.put("open_service_detail", "all")
//						.put("community", "all")
//						.put("area", "all")
//						.build()
//		);

//		printColumn(ImmutableMap.<String, String>builder()
//						.put("open_order", "null")
//						.put("open_store_order", "null")
//						.put("open_quick_order", "null")
//						.put("community_service", "null")
//						.put("open_merchant_info", "null")
//						.put("community_service_category", "null")
//						.put("open_service_detail", "null")
//						.put("community", "null")
//						.put("area", "null")
//
//						.put("open_store_good", "all")
//						.put("open_store_good_order_rel", "all")
////						.put("open_store_good", "all")
//						.build()
//		);

//		printColumnStr(ImmutableMap.<String, String>of("open_store_shopping_cart", "null"));


//		printColumnStr(ImmutableMap.<String, String>builder()
//						.put("open_store_shopping_cart", "all")
//						.put("open_store_good", "all")
//						.put("community_service", "all")
//						.put("open_merchant_info", "all")
//						.put("community_service_category", "all")
//						.put("open_service_detail", "all")
//
//						.put("community", "null")
//						.put("area", "null")
//						.put("open_order", "null")
//						.put("open_store_order", "null")
//						.put("open_quick_order", "null")
//						.put("open_store_good_order_rel", "null")
//						.build()
//		);

		for(String name : getMap().keySet())
		System.out.println(name);
	}

}
