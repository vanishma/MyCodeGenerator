package ${classPath};

/**
 * ${tableComment}
 **/
public class ${fileName} {
    <#list typeName as stu>

        /**
         * ${comment[stu_index]}
         **/
        private ${typeName[stu_index]} ${name[stu_index]};
    </#list>
}