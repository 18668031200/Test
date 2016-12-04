package com.ygdxd.resource.listResource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;

import org.springframework.util.Assert;



public class ListResourceAssembler<E> implements ResourceAssembler<List<E>, ListResources<Resource<E>>>{

	@Override
	public ListResources<Resource<E>> toResource(List<E> entity) {
		// TODO Auto-generated method stub
		return toResource(entity,new SimpleListResourceAssembler<>());
	}
	
	
	public <R extends ResourceSupport> ListResources<R> toResource(List<E> entity,ResourceAssembler<E, R> assembler){
		Assert.notNull(entity, "List must not be null!");
		Assert.notNull(assembler, "ResourceAssembler must not be null!");
		
		List<R> resources=new ArrayList<>(entity.size());
		for (E element : entity) {
			resources.add(assembler.toResource(element));
		}
		return new ListResources<R>(resources);
	}
	
	private static class SimpleListResourceAssembler<E> implements ResourceAssembler<E, Resource<E>>{

		@Override
		public Resource<E> toResource(E entity) {
			// TODO Auto-generated method stub
			return new Resource<>(entity);
		}
		
	}
	

}
