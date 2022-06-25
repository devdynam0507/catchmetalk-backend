package dev.community.gdg.mapping;

public interface MappingService<T, U, M> {

	M mapping(T map1, U map2);

}
