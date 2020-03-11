package com.wly.repository;
import com.wly.entity.AgroChemical;
import com.wly.entity.Element;
import com.wly.entity.ElementType;

import java.util.List;

public interface AgroChemicalRepository {
    public List<AgroChemical> findAll(int index, int limit);
    public AgroChemical findById(long id);
    public AgroChemical findLatestphByid(long id);
    public AgroChemical findLatestmacroByid(long id);
    public AgroChemical findLatestmicroByid(long id);
    public String findCommentByvalue(double value, String elementid);
    public List<AgroChemical> findAllByuserid(String userid,int index,int limit);
    public int countById(long id);
    public int countByUserid(String userid);
    public void save(AgroChemical agroChemical);
    public void update(AgroChemical agroChemical);
    public void deleteById(long id);
    public int count();
}
