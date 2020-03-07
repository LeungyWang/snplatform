package com.wly.repository;
import com.wly.entity.AgroChemical;
import com.wly.entity.Element;

import java.util.List;

public interface ElementRepository {

    public List<Element> findByTypeid(int id);

}
