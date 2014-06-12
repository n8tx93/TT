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

import com.hyc.T1.application.domain.AnswerApplication;
import com.hyc.T1.application.dto.AnswerDTO;
import com.hyc.T1.application.dto.QuestionDTO;
import com.hyc.T1.application.dto.UserDTO;
import com.hyc.T1.core.domain.Answer;
import com.hyc.T1.core.domain.Question;

@Named
@Transactional
public class AnswerApplicationImpl implements AnswerApplication
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
	public AnswerDTO getAnswer(Long id)
	{
		Answer answer = Answer.load(Answer.class, id);
		AnswerDTO answerDTO = new AnswerDTO();
		// 将domain转成VO
		try
		{
			BeanUtils.copyProperties(answerDTO, answer);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		answerDTO.setId((java.lang.Long) answer.getId());
		return answerDTO;
	}

	public AnswerDTO saveAnswer(AnswerDTO answerDTO)
	{
		Answer answer = new Answer();
		try
		{
			BeanUtils.copyProperties(answer, answerDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		answer.save();
		answerDTO.setId((java.lang.Long) answer.getId());
		return answerDTO;
	}

	public void updateAnswer(AnswerDTO answerDTO)
	{
		Answer answer = Answer.get(Answer.class, answerDTO.getId());
		// 设置要更新的值
		try
		{
			BeanUtils.copyProperties(answer, answerDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void removeAnswer(Long id)
	{
		this.removeAnswers(new Long[]
		{ id });
	}

	public void removeAnswers(Long[] ids)
	{
		for (int i = 0; i < ids.length; i++)
		{
			Answer answer = Answer.load(Answer.class, ids[i]);
			answer.remove();
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AnswerDTO> findAllAnswer()
	{
		List<AnswerDTO> list = new ArrayList<AnswerDTO>();
		List<Answer> all = Answer.findAll(Answer.class);
		for (Answer answer : all)
		{
			AnswerDTO answerDTO = new AnswerDTO();
			// 将domain转成VO
			try
			{
				BeanUtils.copyProperties(answerDTO, answer);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			list.add(answerDTO);
		}
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<AnswerDTO> pageQueryAnswer(AnswerDTO queryVo, int currentPage, int pageSize)
	{
		List<AnswerDTO> result = new ArrayList<AnswerDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
		StringBuilder jpql = new StringBuilder(
				"select _answer from Answer _answer   left join _answer.creater  left join _answer.question  where 1=1 ");

		if (queryVo.getName() != null && !"".equals(queryVo.getName()))
		{
			jpql.append(" and _answer.name like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
		}

		if (queryVo.getDescription() != null && !"".equals(queryVo.getDescription()))
		{
			jpql.append(" and _answer.description like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDescription()));
		}

		if (queryVo.getAnswer() != null && !"".equals(queryVo.getAnswer()))
		{
			jpql.append(" and _answer.answer like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getAnswer()));
		}
		Page<Answer> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize)
				.pagedList();
		for (Answer answer : pages.getData())
		{
			AnswerDTO answerDTO = new AnswerDTO();

			// 将domain转成VO
			try
			{
				BeanUtils.copyProperties(answerDTO, answer);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			result.add(answerDTO);
		}
		return new Page<AnswerDTO>(pages.getPageIndex(), pages.getResultCount(), pageSize, result);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserDTO findCreaterByAnswer(Long id)
	{
		String jpql = "select e from Answer o right join o.creater e where o.id=?";
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
	public QuestionDTO findQuestionByAnswer(Long id)
	{
		String jpql = "select e from Answer o right join o.question e where o.id=?";
		Question result = (Question) getQueryChannelService().createJpqlQuery(jpql).setParameters(new Object[]
		{ id }).singleResult();
		QuestionDTO dto = new QuestionDTO();
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
