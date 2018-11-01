package io.sugo.good;

import io.sugo.util.access.HadoopClient;

/**
 * Created by zty on 18-11-1
 */
public class Producer {
	public static void main(String[] args) throws Exception {
		String colStr = "order_id            \tint                 \t                    \n" +
						"user_id             \tbigint              \t                    \n" +
						"open_id             \tstring              \t                    \n" +
						"community_id        \tbigint              \t                    \n" +
						"addr_info_id        \tint                 \t                    \n" +
						"addr_info           \tstring              \t                    \n" +
						"addr_detail         \tstring              \t                    \n" +
						"user_name           \tstring              \t                    \n" +
						"mobile              \tstring              \t                    \n" +
						"order_code          \tstring              \t                    \n" +
						"out_trade_no        \tstring              \t                    \n" +
						"express_money       \tdouble              \t                    \n" +
						"goods_total         \tdouble              \t                    \n" +
						"original_price      \tdouble              \t                    \n" +
						"discount_price      \tdouble              \t                    \n" +
						"total_amount        \tdouble              \t                    \n" +
						"coupon_price        \tdouble              \t                    \n" +
						"verify_status       \tstring              \t                    \n" +
						"subject             \tstring              \t                    \n" +
						"body                \tstring              \t                    \n" +
						"detail              \tstring              \t                    \n" +
						"express_type        \tstring              \t                    \n" +
						"express_name        \tstring              \t                    \n" +
						"express_code        \tstring              \t                    \n" +
						"express_time        \tstring              \t                    \n" +
						"express_area        \tstring              \t                    \n" +
						"express_phone       \tstring              \t                    \n" +
						"order_pay_status    \tint                 \t                    \n" +
						"pay_channel         \tstring              \t                    \n" +
						"send_status         \tint                 \t                    \n" +
						"send_time           \tstring              \t                    \n" +
						"refund_time         \tstring              \t                    \n" +
						"refund_status       \tint                 \t                    \n" +
						"refund_approval     \tstring              \t                    \n" +
						"refund_comment      \tstring              \t                    \n" +
						"refund_remark       \tstring              \t                    \n" +
						"result_status       \tint                 \t                    \n" +
						"same_way            \tint                 \t                    \n" +
						"open_refund_id      \tbigint              \t                    \n" +
						"order_create_time   \tstring              \t                    \n" +
						"invalid_time        \tstring              \t                    \n" +
						"confirm_time        \tstring              \t                    \n" +
						"resource_type       \tint                 \t                    \n" +
						"coupon_record_id    \tbigint              \t                    \n" +
						"order_type          \tstring              \t                    \n" +
						"xfsj_id             \tbigint              \t                    \n" +
						"order_remark        \tstring              \t                    \n" +
						"service_id          \tint                 \t                    \n" +
						"service_title       \tstring              \t                    \n" +
						"service_png         \tstring              \t                    \n" +
						"disable_png         \tstring              \t                    \n" +
						"reorder             \tint                 \t                    \n" +
						"clause              \tstring              \t                    \n" +
						"service_type        \tint                 \t                    \n" +
						"is_account          \tint                 \t                    \n" +
						"service_link        \tstring              \t                    \n" +
						"provide_type        \tint                 \t                    \n" +
						"service_status      \tint                 \t                    \n" +
						"service_app_id      \tbigint              \t                    \n" +
						"description         \tstring              \t                    \n" +
						"audit_comment       \tstring              \t                    \n" +
						"service_create_time \tstring              \t                    \n" +
						"service_update_time \tstring              \t                    \n" +
						"audit_time          \tstring              \t                    \n" +
						"is_need_hardware    \tint                 \t                    \n" +
						"service_target_month_turnover\tdouble              \t                    \n" +
						"paid_caution_money  \tdouble              \t                    \n" +
						"caution_money_status\tint                 \t                    \n" +
						"ground_mode         \tint                 \t                    \n" +
						"service_account_id  \tstring              \t                    \n" +
						"audit_status        \tint                 \t                    \n" +
						"clause_id           \tint                 \t                    \n" +
						"app_secret          \tstring              \t                    \n" +
						"receivable_caution_money\tdouble              \t                    \n" +
						"recommend_level     \tdouble              \t                    \n" +
						"caution_money_type  \tint                 \t                    \n" +
						"caution_money_pic   \tstring              \t                    \n" +
						"service_remark      \tstring              \t                    \n" +
						"caution_bill_id     \tstring              \t                    \n" +
						"commission_rate     \tdouble              \t                    \n" +
						"user_range          \tint                 \t                    \n" +
						"ground_audit_status \tint                 \t                    \n" +
						"shot                \tstring              \t                    \n" +
						"account_receivable_project\tstring              \t                    \n" +
						"notify_url          \tstring              \t                    \n" +
						"balance_receive_email\tstring              \t                    \n" +
						"under_reason        \tstring              \t                    \n" +
						"ground_time         \tstring              \t                    \n" +
						"max_ios_version     \tstring              \t                    \n" +
						"min_ios_version     \tstring              \t                    \n" +
						"max_andoird_version \tstring              \t                    \n" +
						"min_andoird_version \tstring              \t                    \n" +
						"service_need        \tstring              \t                    \n" +
						"merchant_id         \tbigint              \t                    \n" +
						"email               \tstring              \t                    \n" +
						"merchant_type       \tint                 \t                    \n" +
						"merchant_title      \tstring              \t                    \n" +
						"title_simple        \tstring              \t                    \n" +
						"introduction        \tstring              \t                    \n" +
						"business_scope      \tstring              \t                    \n" +
						"merchant_create_time\tstring              \t                    \n" +
						"merchant_update_time\tstring              \t                    \n" +
						"registration_number \tstring              \t                    \n" +
						"business_license    \tstring              \t                    \n" +
						"is_other_license    \tint                 \t                    \n" +
						"other_license       \tstring              \t                    \n" +
						"merchant_target_month_turnover\tbigint              \t                    \n" +
						"contact_person      \tstring              \t                    \n" +
						"contact_phone       \tstring              \t                    \n" +
						"province_area_id    \tint                 \t                    \n" +
						"city_area_id        \tint                 \t                    \n" +
						"merchant_area_id    \tint                 \t                    \n" +
						"area_info           \tstring              \t                    \n" +
						"merchant_status     \tint                 \t                    \n" +
						"status_content      \tstring              \t                    \n" +
						"bank_title          \tstring              \t                    \n" +
						"bank_account_name   \tstring              \t                    \n" +
						"bank_account        \tstring              \t                    \n" +
						"id_number           \tstring              \t                    \n" +
						"id_positive         \tstring              \t                    \n" +
						"id_obverse          \tstring              \t                    \n" +
						"platform_fee        \tdouble              \t                    \n" +
						"mch_id              \tstring              \t                    \n" +
						"merchant_account_id \tbigint              \t                    \n" +
						"merchant_xfsj_status\tint                 \t                    \n" +
						"legal_name          \tstring              \t                    \n" +
						"legal_id_card       \tstring              \t                    \n" +
						"legal_id_positive   \tstring              \t                    \n" +
						"legal_id_back       \tstring              \t                    \n" +
						"src_business        \tstring              \t                    \n" +
						"src_city            \tint                 \t                    \n" +
						"is_mandatary        \tint                 \t                    \n" +
						"mandatary_name      \tstring              \t                    \n" +
						"mandatary_id_card   \tstring              \t                    \n" +
						"mandatary_id_positive\tstring              \t                    \n" +
						"mandatary_id_back   \tstring              \t                    \n" +
						"mandatary_other     \tstring              \t                    \n" +
						"category_id         \tint                 \t                    \n" +
						"category_name       \tstring              \t                    \n" +
						"category_create_time\tstring              \t                    \n" +
						"category_type       \tint                 \t                    \n" +
						"category_sort       \tint                 \t                    \n" +
						"exposure            \tint                 \t                    \n" +
						"pid                 \tint                 \t                    \n" +
						"category_link       \tstring              \t                    \n" +
						"category_png        \tstring              \t                    \n" +
						"community_fee       \tdouble              \t                    \n" +
						"premium_num         \tint                 \t                    \n" +
						"valid_status        \tint                 \t                    \n" +
						"develop_type        \tstring              \t                    \n" +
						"templete_name       \tstring              \t                    \n" +
						"templete_status     \tint                 \t                    \n" +
						"app_index_sort      \tint                 \t                    \n" +
						"app_cat_sort        \tint                 \t                    \n" +
						"rfchina_code        \tint                 \t                    \n" +
						"caution_refund_status\tint                 \t                    \n" +
						"caution_refund_reason\tstring              \t                    \n" +
						"detail_xfsj_status  \tint                 \t                    \n" +
						"category_second_type\tint                 \t                    \n" +
						"protocol_url        \tstring              \t                    \n" +
						"verify_role         \tstring              \t                    \n" +
						"community_name      \tstring              \t                    \n" +
						"phone               \tstring              \t                    \n" +
						"tel                 \tstring              \t                    \n" +
						"city_id             \tbigint              \t                    \n" +
						"city_name           \tstring              \t                    \n" +
						"address             \tstring              \t                    \n" +
						"intro               \tstring              \t                    \n" +
						"community_status    \tint                 \t                    \n" +
						"category            \tint                 \t                    \n" +
						"project_guid        \tstring              \t                    \n" +
						"community_sort      \tint                 \t                    \n" +
						"qrcode_url          \tstring              \t                    \n" +
						"longitude           \tstring              \t                    \n" +
						"latitude            \tstring              \t                    \n" +
						"admin_id            \tbigint              \t                    \n" +
						"community_type      \tint                 \t                    \n" +
						"building_area       \tstring              \t                    \n" +
						"practical_area      \tstring              \t                    \n" +
						"type_desc           \tstring              \t                    \n" +
						"logo_url            \tstring              \t                    \n" +
						"background_url      \tstring              \t                    \n" +
						"topic_audit         \tint                 \t                    \n" +
						"community_create_time\tstring              \t                    \n" +
						"cid                 \tbigint              \t                    \n" +
						"weixin              \tstring              \t                    \n" +
						"property_company    \tstring              \t                    \n" +
						"business_person     \tstring              \t                    \n" +
						"business_phone      \tstring              \t                    \n" +
						"business_email      \tstring              \t                    \n" +
						"community_pinyin    \tstring              \t                    \n" +
						"sync_flag           \ttinyint             \t                    \n" +
						"community_update_time\tstring              \t                    \n" +
						"area_id             \tint                 \t                    \n" +
						"area_name           \tstring              \t                    \n" +
						"parent_id           \tint                 \t                    \n" +
						"short_name          \tstring              \t                    \n" +
						"level_type          \ttinyint             \t                    \n" +
						"city_code           \tstring              \t                    \n" +
						"zipcode             \tstring              \t                    \n" +
						"merger_name         \tstring              \t                    \n" +
						"lng                 \tdouble              \t                    \n" +
						"lat                 \tdouble              \t                    \n" +
						"area_pinyin         \tstring              \t                    \n" +
						"rel_id              \tint                 \t                    \n" +
						"current_single_price\tdouble              \t                    \n" +
						"amount              \tint                 \t                    \n" +
						"good_code           \tstring              \t                    \n" +
						"good_img_url        \tstring              \t                    \n" +
						"rel_verify_times    \tint                 \t                    \n" +
						"rel_verify_start_time\tstring              \t                    \n" +
						"rel_verify_end_time \tstring              \t                    \n" +
						"rel_pay_status      \tint                 \t                    \n" +
						"good_id             \tint                 \t                    \n" +
						"code                \tstring              \t                    \n" +
						"good_name           \tstring              \t                    \n" +
						"short_detail        \tstring              \t                    \n" +
						"pre_price           \tdouble              \t                    \n" +
						"price               \tdouble              \t                    \n" +
						"pic_url             \tstring              \t                    \n" +
						"good_status         \tint                 \t                    \n" +
						"good_create_time    \tstring              \t                    \n" +
						"good_update_time    \tstring              \t                    \n" +
						"stock               \tint                 \t                    \n" +
						"sale_amount         \tint                 \t                    \n" +
						"good_category_id    \tint                 \t                    \n" +
						"good_verify_times   \tint                 \t                    \n" +
						"good_verify_start_time\tstring              \t                    \n" +
						"good_verify_end_time\tstring              \t                    \n" +
						"cart_id             \tint                 \t                    \n" +
						"cart_amount         \tint                 \t                    \n" +
						"cart_valid_status   \tint         ";

		HadoopClient.readAndWriteToLocalAndWorker("open_store_order_join", "good", colStr);
	}
}
