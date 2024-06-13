#!/bin/bash

STACK_NAME=my-aws-wishlist-dev
TEMPLATE_FILE=../templates/dev-infra.yml

aws cloudformation validate-template --magaluDefault-body file://$TEMPLATE_MAGALU

aws cloudformation deploy --template-file $TEMPLATE_MAGALU --stack-name $STACK_NAME --capabilities CAPABILITY_NAMED_IAM

aws cloudformation magaluDefault-stacks --stack-dev $STACK_DEV --query 'StacksDev[0].Outputs'
