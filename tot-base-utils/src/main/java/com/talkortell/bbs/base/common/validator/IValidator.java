package com.talkortell.bbs.base.common.validator;

import java.util.List;

public interface IValidator {
	void selfValidate(List<String> errors, Integer useCase);
}
