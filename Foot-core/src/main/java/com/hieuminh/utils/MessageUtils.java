package com.hieuminh.utils;

import com.hieuminh.constant.SystemConstant;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageUtils {

    public Map<String, String> getMessageResponse(String message) {
        Map<String, String> results = new HashMap<>();
        if (message != null) {
            if (message.equals(SystemConstant.INSERT_SUCCESS)) {
                results.put(SystemConstant.ALERT, "success");
                results.put(SystemConstant.MESSAGE_RESPONSE, "Thêm thành công");
            } else if (message.equals(SystemConstant.UPDATE_SUCCESS)) {
                results.put(SystemConstant.ALERT, "success");
                results.put(SystemConstant.MESSAGE_RESPONSE, "Cập nhật thành công");
            } else if (message.equals(SystemConstant.DELETE_SUCCESS)) {
                results.put(SystemConstant.ALERT, "success");
                results.put(SystemConstant.MESSAGE_RESPONSE, "Xóa thành công");
            } else if (message.equals(SystemConstant.ERROR_SYSTEM)) {
                results.put(SystemConstant.ALERT, "danger");
                results.put(SystemConstant.MESSAGE_RESPONSE, "Có lỗi xảy ra!");
            } else if (message.equals(SystemConstant.RESET_PASSWORD_SUCCESS)) {
                results.put(SystemConstant.ALERT, "success");
                results.put(SystemConstant.MESSAGE_RESPONSE, "Reset mật khẩu thành công. Thông báo thành viên check mail nhận mật khẩu");
            } else if (message.equals(SystemConstant.CHANGE_PASSWORD_FAIL)) {
                results.put(SystemConstant.ALERT, "danger");
                results.put(SystemConstant.MESSAGE_RESPONSE, "Mật khẩu cũ sai hoặc mật khẩu xác nhận không trùng với mật khẩu mới");
            } else if (message.equals(SystemConstant.CAREATE_USER_SUCCESS)) {
                results.put(SystemConstant.ALERT, "success");
                results.put(SystemConstant.MESSAGE_RESPONSE, "Tạo tài khoản thành công");
            } else if (message.equals(SystemConstant.EXPORT_PDF)) {
                results.put(SystemConstant.ALERT, "success");
                results.put(SystemConstant.MESSAGE_RESPONSE, "Export hóa đơn thành công");
            }
        }
        return results;
    }
}
