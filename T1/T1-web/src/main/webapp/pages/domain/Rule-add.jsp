<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal">
	           <div class="form-group">
                    <label class="col-lg-3 control-label">name:</label>
                    <div class="col-lg-9">
                           <input name="name" style="display:inline; width:94%;" class="form-control"  type="text" dataType="Require" id="nameID" />
<span class="required">*</span>    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">description:</label>
                    <div class="col-lg-9">
                           <input name="description" style="display:inline; width:94%;" class="form-control"  type="text"  id="descriptionID" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">password:</label>
                    <div class="col-lg-9">
                           <input name="password" style="display:inline; width:94%;" class="form-control"  type="text"  id="passwordID" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">qType:</label>
                    <div class="col-lg-9">
                           <input name="qType" style="display:inline; width:94%;" class="form-control"  type="text"  id="qTypeID" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">kType:</label>
                    <div class="col-lg-9">
                           <input name="kType" style="display:inline; width:94%;" class="form-control"  type="text"  id="kTypeID" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">answerCount:</label>
                    <div class="col-lg-9">
                           <input name="answerCount" style="display:inline; width:94%;" class="form-control"  type="text"  dataType="Number" require="false" id="answerCountID" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">releaseRule:</label>
                    <div class="col-lg-9">
                           <div class="btn-group select" id="releaseRuleID"></div>
	                       <input type="hidden" id="releaseRuleID_"  name="releaseRule" />
    </div>
</div>
</form>
<script type="text/javascript">
    var selectItems = {};
                                                            var contents = [{title:'请选择', value: ''}];
        contents.push({title:'规则二' , value:'2'});
        contents.push({title:'规则一' , value:'1'});
        selectItems['releaseRuleID'] = contents;
        </script>
</body>
</html>