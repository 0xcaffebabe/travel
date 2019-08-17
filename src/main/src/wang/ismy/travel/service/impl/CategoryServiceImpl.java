package wang.ismy.travel.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import wang.ismy.travel.dao.CategoryDAO;
import wang.ismy.travel.dao.impl.CategoryDAOImpl;
import wang.ismy.travel.entity.Category;
import wang.ismy.travel.service.CategoryService;
import wang.ismy.travel.util.JedisUtils;

import java.util.List;

/**
 * @author MY
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO dao = new CategoryDAOImpl();

    @Override
    public List<Category> findAll() {

        Jedis jedis = JedisUtils.get();

        String str = jedis.get("category-list");

        jedis.close();

        if (StringUtils.isEmpty(str)){
            List<Category> all = dao.findAll();
            jedis.set("category-list",new Gson().toJson(all));
            return all;
        }else{
            return new Gson().fromJson(str,new TypeToken<List<Category>>(){}.getType());
        }

    }
}
