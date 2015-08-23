<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
.cat-float{
    width:74px;
    position: fixed;
    _position:absolute;
    top:300px;
    bottom:50px;
	margin-left:155px;

}
</style>
<div id="J_Float" class="cat-float">
                <img src="${approot}/static/style/taoke/images/float_nav.gif" border="0" usemap="#M_Float" alt="浮出导航" />
                <map name="M_Float" id="M_Float">
                    <area target="_self" shape="rect" coords="0,10,74,40" href ="#floor1" alt="女装" />
                    <area target="_self" shape="rect" coords="0,57,74,90" href ="#floor2" alt="男装" />
                    <area target="_self" shape="rect" coords="0,104,74,137" href ="#floor3" alt="数码" />
                    <area target="_self" shape="rect" coords="0,151,74,184" href ="#floor4" alt="鞋子" />
                    <area target="_self" shape="rect" coords="0,199,74,232" href ="#floor5" alt="箱包" />
                    <area target="_self" shape="rect" coords="0,246,74,279" href ="#floor6" alt="数码" />
                    <area target="_self" shape="rect" coords="0,293,74,325" href ="#floor7" alt="配饰" />
                </map>
</div>

