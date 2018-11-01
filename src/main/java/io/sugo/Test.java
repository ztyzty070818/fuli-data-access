package io.sugo;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 18-10-24
 */
public class Test {
	public static void main(String[] args) {
		String str = "open_store_order.id as order_id,\n" +
						"user_id,\n" +
						"open_id,\n" +
						"community_id,\n" +
						"addr_info_id,\n" +
						"addr_info,\n" +
						"addr_detail,\n" +
						"user_name,\n" +
						"mobile,\n" +
						"order_code,\n" +
						"out_trade_no,\n" +
						"express_money,\n" +
						"goods_total,\n" +
						"original_price,\n" +
						"discount_price,\n" +
						"total_amount,\n" +
						"coupon_price,\n" +
						"verify_status,\n" +
						"subject,\n" +
						"body,\n" +
						"detail,\n" +
						"express_type,\n" +
						"express_name,\n" +
						"express_code,\n" +
						"express_time,\n" +
						"express_area,\n" +
						"express_phone,\n" +
						"open_store_order.pay_status as order_pay_status,\n" +
						"pay_channel,\n" +
						"send_status,\n" +
						"send_time,\n" +
						"refund_time,\n" +
						"refund_status,\n" +
						"refund_approval,\n" +
						"refund_comment,\n" +
						"refund_remark,\n" +
						"result_status,\n" +
						"same_way,\n" +
						"open_refund_id,\n" +
						"open_store_order.create_time as order_create_time,\n" +
						"invalid_time,\n" +
						"confirm_time,\n" +
						"resource_type,\n" +
						"coupon_record_id,\n" +
						"open_store_order.type as order_type,\n" +
						"xfsj_id,\n" +
						"open_store_order.remark as order_remark,\n" +
						"\n" +
						"\n" +
						"community_service.id as service_id,\n" +
						"community_service.title as service_title,\n" +
						"community_service.png as service_png,\n" +
						"disable_png,\n" +
						"reorder,\n" +
						"clause,\n" +
						"community_service.type as service_type,\n" +
						"is_account,\n" +
						"community_service.link as service_link,\n" +
						"provide_type,\n" +
						"community_service.status as service_status,\n" +
						"community_service.app_id as service_app_id,\n" +
						"description,\n" +
						"audit_comment,\n" +
						"community_service.create_time as service_create_time,\n" +
						"community_service.update_time as service_update_time,\n" +
						"audit_time,\n" +
						"is_need_hardware,\n" +
						"community_service.target_month_turnover as service_target_month_turnover,\n" +
						"paid_caution_money,\n" +
						"caution_money_status,\n" +
						"ground_mode,\n" +
						"community_service.account_id as service_account_id,\n" +
						"audit_status,\n" +
						"clause_id,\n" +
						"app_secret,\n" +
						"receivable_caution_money,\n" +
						"recommend_level,\n" +
						"caution_money_type,\n" +
						"caution_money_pic,\n" +
						"community_service.remark as service_remark,\n" +
						"caution_bill_id,\n" +
						"commission_rate,\n" +
						"user_range,\n" +
						"ground_audit_status,\n" +
						"shot,\n" +
						"account_receivable_project,\n" +
						"notify_url,\n" +
						"balance_receive_email,\n" +
						"under_reason,\n" +
						"ground_time,\n" +
						"max_ios_version,\n" +
						"min_ios_version,\n" +
						"max_andoird_version,\n" +
						"min_andoird_version,\n" +
						"service_need,\n" +
						"\n" +
						"open_merchant_info.id as merchant_id,\n" +
						"email,\n" +
						"open_merchant_info.type as merchant_type,\n" +
						"open_merchant_info.title as merchant_title,\n" +
						"title_simple,\n" +
						"introduction,\n" +
						"business_scope,\n" +
						"open_merchant_info.create_time as merchant_create_time,\n" +
						"open_merchant_info.update_time as merchant_update_time,\n" +
						"registration_number,\n" +
						"business_license,\n" +
						"is_other_license,\n" +
						"other_license,\n" +
						"open_merchant_info.target_month_turnover as merchant_target_month_turnover,\n" +
						"contact_person,\n" +
						"contact_phone,\n" +
						"province_area_id,\n" +
						"city_area_id,\n" +
						"open_merchant_info.area_id as merchant_area_id,\n" +
						"area_info,\n" +
						"open_merchant_info.status as merchant_status,\n" +
						"status_content,\n" +
						"bank_title,\n" +
						"bank_account_name,\n" +
						"bank_account,\n" +
						"id_number,\n" +
						"id_positive,\n" +
						"id_obverse,\n" +
						"platform_fee,\n" +
						"mch_id,\n" +
						"open_merchant_info.account_id as merchant_account_id,\n" +
						"open_merchant_info.xfsj_status as merchant_xfsj_status,\n" +
						"legal_name,\n" +
						"legal_id_card,\n" +
						"legal_id_positive,\n" +
						"legal_id_back,\n" +
						"src_business,\n" +
						"src_city,\n" +
						"is_mandatary,\n" +
						"mandatary_name,\n" +
						"mandatary_id_card,\n" +
						"mandatary_id_positive,\n" +
						"mandatary_id_back,\n" +
						"mandatary_other,\n" +
						"\n" +
						"community_service_category.id as category_id,\n" +
						"community_service_category.name as category_name,\n" +
						"community_service_category.create_time as category_create_time,\n" +
						"community_service_category.type as category_type,\n" +
						"community_service_category.sort as category_sort,\n" +
						"exposure,\n" +
						"pid,\n" +
						"community_service_category.link as category_link,\n" +
						"community_service_category.png as category_png,\n" +
						"community_fee,\n" +
						"premium_num,\n" +
						"valid_status,\n" +
						"\n" +
						"develop_type,\n" +
						"templete_name,\n" +
						"templete_status,\n" +
						"app_index_sort,\n" +
						"app_cat_sort,\n" +
						"rfchina_code,\n" +
						"caution_refund_status,\n" +
						"caution_refund_reason,\n" +
						"open_service_detail.xfsj_status as detail_xfsj_status,\n" +
						"category_second_type,\n" +
						"protocol_url,\n" +
						"verify_role,\n" +
						"\n" +
						"community.name as community_name,\n" +
						"phone,\n" +
						"tel,\n" +
						"city_id,\n" +
						"city_name,\n" +
						"address,\n" +
						"intro,\n" +
						"community.status as community_status,\n" +
						"category,\n" +
						"project_guid,\n" +
						"community.sort as community_sort,\n" +
						"qrcode_url,\n" +
						"longitude,\n" +
						"latitude,\n" +
						"admin_id,\n" +
						"community.type as community_type,\n" +
						"building_area,\n" +
						"practical_area,\n" +
						"type_desc,\n" +
						"logo_url,\n" +
						"background_url,\n" +
						"topic_audit,\n" +
						"community.create_time as community_create_time,\n" +
						"cid,\n" +
						"weixin,\n" +
						"property_company,\n" +
						"business_person,\n" +
						"business_phone,\n" +
						"business_email,\n" +
						"community.pinyin as community_pinyin,\n" +
						"sync_flag,\n" +
						"community.update_time as community_update_time,\n" +
						"\n" +
						"area.id as area_id,\n" +
						"area.name as area_name,\n" +
						"parent_id,\n" +
						"short_name,\n" +
						"level_type,\n" +
						"city_code,\n" +
						"zipcode,\n" +
						"merger_name,\n" +
						"lng,\n" +
						"lat,\n" +
						"area.pinyin as area_pinyin,\n" +
						"\n" +
						"open_store_good_order_rel.id as rel_id,\n" +
						"current_single_price,\n" +
						"amount,\n" +
						"good_code,\n" +
						"good_img_url,\n" +
						"open_store_good_order_rel.verify_times as rel_verify_times,\n" +
						"open_store_good_order_rel.verify_start_time as rel_verify_start_time,\n" +
						"open_store_good_order_rel.verify_end_time as rel_verify_end_time,\n" +
						"open_store_good_order_rel.pay_status as rel_pay_status,\n" +
						"\n" +
						"open_store_good.id as good_id,\n" +
						"code,\n" +
						"open_store_good.name as good_name,\n" +
						"short_detail,\n" +
						"pre_price,\n" +
						"price,\n" +
						"pic_url,\n" +
						"open_store_good.status as good_status,\n" +
						"open_store_good.create_time as good_create_time,\n" +
						"open_store_good.update_time as good_update_time,\n" +
						"stock,\n" +
						"sale_amount,\n" +
						"open_store_good.category_id as good_category_id,\n" +
						"open_store_good.verify_times as good_verify_times,\n" +
						"open_store_good.verify_start_time as good_verify_start_time,\n" +
						"open_store_good.verify_end_time as good_verify_end_time,\n" +
						"\n" +
						"null,\n" +
						"null,\n" +
						"null,\n" +
						"null";
		for(int i =0; i< StringUtils.countMatches(str,","); i++)
			System.out.println("null,");
		System.out.println("null");
	}
}
