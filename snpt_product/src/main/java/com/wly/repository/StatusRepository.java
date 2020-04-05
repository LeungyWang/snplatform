package com.wly.repository;
        import com.wly.entity.Status;
        import java.util.List;

public interface StatusRepository {
    //查询所有的状态
    public  List<Status> findAll(int index, int limit);
    //通过Id查找状态
    public Status findById(String id);
    //新增状态
    public void save(Status status);
    //更改状态
    public void update(Status status);
    //删除状态
    public void deleteById(String id);
    public int count();
}
