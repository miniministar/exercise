package sharding.yml.service;

import sharding.yml.dao.DictionaryDao;
import sharding.yml.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

    @Autowired
    private DictionaryDao dictionaryDao;

    public long addOne(Dictionary dictionary) {
        this.dictionaryDao.addOne(dictionary);
        return dictionary.getDictionaryId();
    }
}
