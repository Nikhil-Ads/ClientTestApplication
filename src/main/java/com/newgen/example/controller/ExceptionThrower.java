package com.newgen.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.newgen.example.error.constants.CabinetErrorConstants;
import com.newgen.example.error.constants.CommonErrorConstants;
import com.newgen.example.exception.CustomException;
import com.newgen.example.util.ErrorMessagesUtil;

public class ExceptionThrower {
	
	@Autowired
	ErrorMessagesUtil errorMessageUtil;
	
	public void throwInvalidCabinetException() throws CustomException{
		throwCustomException(CabinetErrorConstants.PREFIX + CabinetErrorConstants.INVALID_CABINET_ID, HttpStatus.BAD_REQUEST);
	}
	
	public void throwLockExistsException() throws CustomException {
		throwCustomException(CommonErrorConstants.PREFIX + CommonErrorConstants.LOCK_ALREADY_EXISTS, HttpStatus.LOCKED);
	}
	
	public void throwFolderContentExistsException() throws CustomException {
		throwCustomException(CommonErrorConstants.PREFIX + CommonErrorConstants.FOLDERS_AND_CONTENTS_ASSOCIATED, HttpStatus.BAD_REQUEST);
	}
	
	public void throwDatabaseFailureException() throws CustomException{
		throwCustomException(CommonErrorConstants.PREFIX + CommonErrorConstants.DATABASE_CONNECTION_FAILURE, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	public void throwFolderNotFoundException() throws CustomException {
		throwCustomException(CommonErrorConstants.PREFIX + CommonErrorConstants.FOLDER_NOT_FOUND, HttpStatus.NOT_FOUND);
	}
	
	public void throwUnknownErrorException() throws CustomException{
		throwCustomException(CommonErrorConstants.PREFIX + CommonErrorConstants.UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public void throwVersionConflictException() throws CustomException {
		throwCustomException(CommonErrorConstants.PREFIX + CommonErrorConstants.VERSION_CONFLICT, HttpStatus.CONFLICT);
	}
	
	public void throwInvalidTenantException() throws CustomException{
		throwCustomException(CommonErrorConstants.PREFIX + CommonErrorConstants.INVALID_TENANT_ID, HttpStatus.BAD_REQUEST);
	}
	
	public void throwInvalidOrgException() throws CustomException {
		throwCustomException(CommonErrorConstants.PREFIX + CommonErrorConstants.INVALID_ORG, HttpStatus.BAD_REQUEST);
	}
	
	
	public void throwECMServiceException() throws CustomException{
		throwCustomException(CommonErrorConstants.PREFIX + CommonErrorConstants.ECM_SERVICE_DOWN, HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	public void throwNoResultsFoundException() throws CustomException {
		throwCustomException(CommonErrorConstants.PREFIX + CommonErrorConstants.NO_RESULTS_FOUND, HttpStatus.NOT_FOUND);
	}
	
	public void throwCustomException(String code, HttpStatus httpStatus) throws CustomException{
		throw new CustomException(Integer.parseInt(code), errorMessageUtil.getErrorMessage(code), httpStatus);
	}
	
	public void throwInvalidQueryParameterException(String parameter) throws CustomException{
		Integer code=Integer.parseInt(CommonErrorConstants.PREFIX + CommonErrorConstants.NO_PARAMS_FOUND);		
		throw new CustomException(code, errorMessageUtil.getErrorMessage(code) + parameter, HttpStatus.NOT_FOUND);
	}

}
