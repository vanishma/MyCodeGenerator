package ${classPath};

public class ${fileName} {
    <#list type as stu>
        private ${stu.type} ${stu.name};
    </#list>
}