package ${classPath};

<#list importPackages as pck>
import ${pck};
</#list>
<#if lombok>
import lombok.Data;
</#if>

/**
 * ${tableComment}
 **/
 <#if lombok>
@Data
 </#if>
public class ${fileName} {
<#list typeName as stu>

    /**
     * ${comment[stu_index]}
     **/
    private ${typeName[stu_index]} ${name[stu_index]};
</#list>

<#if !lombok>
<#list typeName as stu>
    public ${typeName[stu_index]} get${name[stu_index]?cap_first}(){
        return ${name[stu_index]};
    }

    public void set${name[stu_index]?cap_first}(${typeName[stu_index]} ${name[stu_index]}){
        this.${name[stu_index]} = ${name[stu_index]};
    }

</#list>
</#if>
}