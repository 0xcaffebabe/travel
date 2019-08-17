package wang.ismy.travel.entity;

import lombok.Data;

import java.util.List;

@Data
public class Route {

    private Integer rid;

    private String rname;

    private Double price;

    private String routeIntroduce;

    private Boolean rflag;

    private String rdate;

    private String isThemeTour;

    private Integer count;

    private Integer cid;

    private String rimage;

    private String sourceId;

    private Integer sid;

    private List<RouteImg> imgList;

    private Seller seller;

}
