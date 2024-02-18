package cn.arcy.jportal.portal.mapstruct;

import org.mapstruct.MapperConfig;

@MapperConfig
public interface MapStructConfig {

    /*@BeforeMapping
    default void mapToEnum(Object source, @MappingTarget AbstractEnum target)
    {
        //EnumUtil.codeOf(target.getClass());
        System.out.println("test");
    }*/
}
