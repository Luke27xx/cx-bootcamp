# Introduction #

package com.jds.architecture.service.dao.stmtgenerator;
+StatementGenEmployee
StatementGenProject
...Skill
...SkillCategory


package com.jds.architecture.service.dao.assembler;
+EmployeeAssembler
ProjectyAssembler
Skill..
SkillCategory..


package com.jds.architecture.service.dao;
+EmployeeDAO
ProjectDAO
SkillDAO
SkillCategoryDAO


package com.jds.businesscomponent.hr;
EmployeeBC :
> +createEmployee
> +searchEmployee
> Search??
> Update

ProjectBC
SkillCategoryBC
SkillBC

# Details #

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages