package com.wly.repository;
import com.wly.entity.AgroChemical;

import java.util.List;

public interface AgroChemicalRepository {
    public List<AgroChemical> findAll(int index, int limit);
    public AgroChemical findById(long id);
    public List<AgroChemical> findAllByuserid(String userid,int index,int limit);
    public int countById(long id);
    public int countByUserid(String userid);
    public void save(AgroChemical agroChemical);
    public void update(AgroChemical agroChemical);
    public void deleteById(long id);
    public int count();
}
