package com.hyc.T1.application.impl.domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.openkoala.koala.auth.core.domain.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyc.T1.application.domain.QuestionApplication;
import com.hyc.T1.application.dto.QuestionDTO;
import com.hyc.T1.application.dto.RuleDTO;
import com.hyc.T1.application.dto.UserDTO;
import com.hyc.T1.core.domain.Question;
import com.hyc.T1.core.domain.Rule;

@Named
@Transactional
public class QuestionApplicationImpl implements QuestionApplication
{

	private QueryChannelService queryChannel;

	private QueryChannelService getQueryChannelService()
	{
		if (queryChannel == null)
		{
			queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
		}
		return queryChannel;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public QuestionDTO getQuestion(Long id)
	{
		Question question = Question.load(Question.class, id);
		QuestionDTO questionDTO = new QuestionDTO();
		// 将domain转成VO
		try
		{
			BeanUtils.copyProperties(questionDTO, question);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		questionDTO.setId((java.lang.Long) question.getId());
		return questionDTO;
	}

	public QuestionDTO saveQuestion(QuestionDTO questionDTO)
	{
		Question question = new Question();
		try
		{
			BeanUtils.copyProperties(question, questionDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		question.save();
		questionDTO.setId((java.lang.Long) question.getId());
		return questionDTO;
	}

	public void updateQuestion(QuestionDTO questionDTO)
	{
		Question question = Question.get(Question.class, questionDTO.getId());
		// 设置要更新的值
		try
		{
			BeanUtils.copyProperties(question, questionDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void removeQuestion(Long id)
	{
		this.removeQuestions(new Long[]
		{ id });
	}

	public void removeQuestions(Long[] ids)
	{
		for (int i = 0; i < ids.length; i++)
		{
			Question question = Question.load(Question.class, ids[i]);
			question.remove();
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QuestionDTO> findAllQuestion()
	{
		List<QuestionDTO> list = new ArrayList<QuestionDTO>();
		List<Question> all = Question.findAll(Question.class);
		for (Question question : all)
		{
			QuestionDTO questionDTO = new QuestionDTO();
			// 将domain转成VO
			try
			{
				BeanUtils.copyProperties(questionDTO, question);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			list.add(questionDTO);
		}
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<QuestionDTO> pageQueryQuestion(QuestionDTO queryVo, int currentPage, int pageSize)
	{
		List<QuestionDTO> result = new ArrayList<QuestionDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
		StringBuilder jpql = new StringBuilder(
				"select _question from Question _question   left join _question.creater  left join _question.rule  where 1=1 ");

		if (queryVo.getName() != null && !"".equals(queryVo.getName()))
		{
			jpql.append(" and _question.name like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
		}

		if (queryVo.getDescription() != null && !"".equals(queryVo.getDescription()))
		{
			jpql.append(" and _question.description like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDescription()));
		}

		if (queryVo.getCorrectAnswer() != null && !"".equals(queryVo.getCorrectAnswer()))
		{
			jpql.append(" and _question.correctAnswer like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCorrectAnswer()));
		}

		if (queryVo.getStatus() != null && !"".equals(queryVo.getStatus()))
		{
			jpql.append(" and _question.status like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getStatus()));
		}

		Page<Question> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize)
				.pagedList();
		for (Question question : pages.getData())
		{
			QuestionDTO questionDTO = new QuestionDTO();

			// 将domain转成VO
			try
			{
				BeanUtils.copyProperties(questionDTO, question);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			result.add(questionDTO);
		}
		return new Page<QuestionDTO>(pages.getPageIndex(), pages.getResultCount(), pageSize, result);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserDTO findCreaterByQuestion(Long id)
	{
		String jpql = "select e from Question o right join o.creater e where o.id=?";
		User result = (User) getQueryChannelService().createJpqlQuery(jpql).setParameters(new Object[]
		{ id }).singleResult();
		UserDTO dto = new UserDTO();
		if (result != null)
		{
			try
			{
				BeanUtils.copyProperties(dto, result);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return dto;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RuleDTO findRuleByQuestion(Long id)
	{
		String jpql = "select e from Question o right join o.rule e where o.id=?";
		Rule result = (Rule) getQueryChannelService().createJpqlQuery(jpql).setParameters(new Object[]
		{ id }).singleResult();
		RuleDTO dto = new RuleDTO();
		if (result != null)
		{
			try
			{
				BeanUtils.copyProperties(dto, result);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return dto;
	}

}
