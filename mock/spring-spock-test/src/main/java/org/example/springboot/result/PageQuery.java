package org.example.springboot.result;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @desc 基础分页请求对象
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageQuery<T> implements Serializable {

    private int pageNum = 1;

    private int pageSize = 10;

    private T params;

    public void startPage() {
        PageHelper.startPage(this.pageNum, this.pageSize);
    }

    public PageResult<T> getPageResult(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        PageResult<T> success = PageResult.success(pageInfo);
        return success;
    }
}
