package wang.ismy.travel.entity;

import lombok.Data;

import java.util.List;

/**
 * @author MY
 */
@Data
public class PageBean<T> {

    private Integer totalCount;

    private Integer totalPage;

    private Integer currentPage;

    private Integer pageSize;

    private List<T> list;
}
