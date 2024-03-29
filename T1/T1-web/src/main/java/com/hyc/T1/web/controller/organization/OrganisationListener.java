package com.hyc.T1.web.controller.organization;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dayatang.domain.InstanceFactory;
import org.openkoala.organisation.application.OrganizationApplication;
import org.openkoala.organisation.domain.Company;

public class OrganisationListener implements ServletContextListener
{

	private OrganizationApplication organizationApplication;

	private OrganizationApplication getOrganizationApplication()
	{
		if (organizationApplication == null)
		{
			organizationApplication = InstanceFactory.getInstance(OrganizationApplication.class);
		}
		return organizationApplication;
	}

	public void contextInitialized(ServletContextEvent event)
	{
		initTopOrganizationIfNecessary(event);
	}

	private void initTopOrganizationIfNecessary(ServletContextEvent event)
	{

		if (getOrganizationApplication().isTopOrganizationExists() == false)
		{
			getOrganizationApplication().createAsTopOrganization(newTopOrganization());
		}

	}

	private Company newTopOrganization()
	{
		return new Company("总公司", "COM-001", "总公司：所有机构的根");
	}

	public void contextDestroyed(ServletContextEvent sce)
	{

	}

}