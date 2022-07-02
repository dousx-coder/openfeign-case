package cn.cruder.dousx.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dousx
 * @date 2022-07-02 18:05
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Authorization {
    private String username;
    private String password;
}
