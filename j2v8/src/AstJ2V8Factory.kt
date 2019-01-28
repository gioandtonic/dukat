package org.jetbrains.dukat.ast.j2v8

import com.eclipsesource.v8.V8
import com.eclipsesource.v8.V8Object
import com.eclipsesource.v8.utils.V8ObjectUtils
import org.jetbrains.dukat.ast.factory.AstNodeFactory
import org.jetbrains.dukat.ast.model.declaration.ClassLikeDeclaration
import org.jetbrains.dukat.ast.model.declaration.Declaration
import org.jetbrains.dukat.ast.model.declaration.ExpressionDeclaration
import org.jetbrains.dukat.ast.model.declaration.InterfaceDeclaration
import org.jetbrains.dukat.ast.model.declaration.MemberDeclaration
import org.jetbrains.dukat.ast.model.declaration.ModifierDeclaration
import org.jetbrains.dukat.ast.model.declaration.ParameterDeclaration
import org.jetbrains.dukat.ast.model.declaration.TypeParameterDeclaration
import org.jetbrains.dukat.ast.model.declaration.types.ParameterValueDeclaration
import org.jetbrains.dukat.ast.model.declaration.types.TypeDeclaration

class AstJ2V8Factory(private val runtime: V8, private val astFactory: AstNodeFactory<Map<String, Any?>> = AstMapFactory()) : AstNodeFactory<V8Object> {

    private fun toV8(node: Map<String, Any?>) = V8ObjectUtils.toV8Object(runtime, node)

    override fun createStringTypeDeclaration(tokens: List<String>) =
            toV8(astFactory.createStringTypeDeclaration(tokens))

    override fun createIndexSignatureDeclaration(indexTypes: List<ParameterDeclaration>, returnType: ParameterValueDeclaration) =
            toV8(astFactory.createIndexSignatureDeclaration(indexTypes, returnType))

    override fun createCallSignatureDeclaration(
            parameters: List<ParameterDeclaration>,
            type: ParameterValueDeclaration,
            typeParameters: List<TypeParameterDeclaration>
    ) = toV8(astFactory.createCallSignatureDeclaration(parameters, type, typeParameters))

    override fun createModifierDeclaration(token: String) = toV8(astFactory.createModifierDeclaration(token))

    override fun createClassDeclaration(
            name: String,
            members: List<MemberDeclaration>,
            typeParameters: List<TypeParameterDeclaration>,
            parentEntities: List<ClassLikeDeclaration>,
            staticMembers: List<MemberDeclaration>
    )
        = toV8(astFactory.createClassDeclaration(name, members, typeParameters, parentEntities, staticMembers))

    override fun createObjectLiteral(members: List<MemberDeclaration>) = toV8(astFactory.createObjectLiteral(members))

    override fun createInterfaceDeclaration(
            name: String,
            members: List<MemberDeclaration>,
            typeParameters: List<TypeParameterDeclaration>,
            parentEntities: List<InterfaceDeclaration>
    ) = toV8(astFactory.createInterfaceDeclaration(name, members, typeParameters, parentEntities))

    override fun createExpression(kind: TypeDeclaration, meta: String?) = toV8(astFactory.createExpression(kind, meta))

    override fun declareVariable(name: String, type: ParameterValueDeclaration)
        = toV8(astFactory.declareVariable(name, type))

    override fun declareProperty(
            name: String,
            type: ParameterValueDeclaration,
            parameters: List<TypeParameterDeclaration>,
            optional: Boolean,
            modifiers: List<ModifierDeclaration>
    )
            = toV8(astFactory.declareProperty(name, type, parameters, optional, modifiers))

    override fun createConstructorDeclaration(parameters: List<ParameterDeclaration>, type: ParameterValueDeclaration, typeParameters: List<TypeParameterDeclaration>, modifiers: List<ModifierDeclaration>)
        = toV8(astFactory.createConstructorDeclaration(parameters, type, typeParameters, modifiers))

    override fun createFunctionDeclaration(
            name: String,
            parameters: Array<ParameterDeclaration>,
            type: ParameterValueDeclaration,
            typeParameters: Array<TypeParameterDeclaration>,
            modifiers: List<ModifierDeclaration>
    ) = toV8(astFactory.createFunctionDeclaration(name, parameters, type, typeParameters, modifiers))

    override fun createMethodSignatureDeclaration(name: String, parameters: Array<ParameterDeclaration>, type: ParameterValueDeclaration, typeParameters: Array<TypeParameterDeclaration>, optional: Boolean, modifiers: List<ModifierDeclaration>)
        = toV8(astFactory.createMethodSignatureDeclaration(name, parameters, type, typeParameters, optional, modifiers))

    override fun createFunctionTypeDeclaration(parameters: Array<ParameterDeclaration>, type: ParameterValueDeclaration)
        = toV8(astFactory.createFunctionTypeDeclaration(parameters, type))

    override fun createTypeDeclaration(value: String, params: Array<ParameterValueDeclaration>)
        = toV8(astFactory.createTypeDeclaration(value, params))

    override fun createParameterDeclaration(name: String, type: ParameterValueDeclaration, initializer: ExpressionDeclaration?, vararg: Boolean)
        = toV8(astFactory.createParameterDeclaration(name, type, initializer, vararg))

    override fun createDocumentRoot(packageName: String, declarations: Array<Declaration>)
        = toV8(astFactory.createDocumentRoot(packageName, declarations))

    override fun createTypeParam(name: String, constraints: Array<ParameterValueDeclaration>) = toV8(astFactory.createTypeParam(name, constraints))
}