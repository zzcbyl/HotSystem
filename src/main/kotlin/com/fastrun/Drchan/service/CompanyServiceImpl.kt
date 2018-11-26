package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.CompanyMapper
import com.fastrun.TempCollection.model.Company
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class CompanyServiceImpl : CompanyService {
    @Resource
    var companyRepository: CompanyMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: Company): Int? {
        return companyRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: Company): Int? {
        return companyRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): Company? {
        return companyRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(name: String, parentID: Int, offset: Int, pageSize: Int, orderBy: String): List<Company>? {
        return companyRepository?.getPaging(name, parentID, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(name: String, parentID: Int): Int? {
        return companyRepository?.getCount(name, parentID)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return companyRepository?.delete(id)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getByName(name: String): Company? {
        return companyRepository?.getByName(name)
    }
}

